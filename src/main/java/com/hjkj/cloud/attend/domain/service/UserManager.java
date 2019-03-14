package com.hjkj.cloud.attend.domain.service;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.domain.repository.*;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl.AbstractBatchRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserManager extends AbstractBatchRepositoryImpl<User> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITerminalRepository terminalRepository;
    @Autowired
    private TerminalManager terminalManager;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserTerminalRepository userTerminalRepository;
    @Autowired
    private IUserPostRepository userPostRepository;

    @Transactional
    public void saveOrUpdateUser(User user) {
        Optional<User>  oldUser = Optional.empty();
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
            //判断工号是否存在
            if (!StringUtils.isEmpty(user.getCardNum())) {
                User isUser = userRepository.findUserByCardNum(user.getCardNum());
                if (null != isUser) {
                    throw new ServiceException(ResultCode.FAILED.code,"工号[" + user.getCardNum() + "]已存在");
                }

            }
        } else {
            oldUser = userRepository.findById(user.getId());
        }
        if (user.getAgent() != null) {
            if (!StringUtils.isEmpty(user.getAgent().getId())) {
                User agent = findUserById(user.getAgent().getId());
                user.setAgent(agent);
            } else {
                user.setAgent(null);
            }
        }

        if (user.getTerminals() != null && user.getTerminals().size() > 0) {
            user.getTerminals().forEach(terminal -> {
                Terminal tm = findTerminalById(terminal.getId());
                boolean res = user.addTerminal(tm);
                if (res) {
                    //更新该终端的信息
                    tm.incRegister();
                    terminalRepository.saveAndFlush(tm);
                }
            });
        } else {
            oldUser.ifPresent(u -> user.setTerminals(u.getTerminals()));
        }

        if (user.getPosts() != null && user.getPosts().size() > 0) {
            user.getPosts().forEach(p -> {
                Post post = findPostById(p.getId());
                user.getPosts().add(post);
            });
        } else {
            oldUser.ifPresent(u -> user.setPosts(u.getPosts()));
        }

        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUserByIds(List<String> userIds) {
        if (userIds == null) {
            throw new ServiceException(ResultCode.FAILED.code,"没有要删除的用户!");
        }
        // 批量查询用户关联的终端
        List<String> tmIds = userRepository.findTmIdsByUserIds(userIds);
        // 批量删除用户
        userRepository.deleteUserPostByUserIds(userIds);
        userRepository.deleteUserTerminalByUserIds(userIds);
        // TODO: 级联删除用户考勤数据
        userRepository.deleteUserBatch(userIds);
        // TODO:批量更新终端的注册人数
        if (tmIds != null && tmIds.size() > 0) {
            tmIds.forEach(tmId -> {
                terminalRepository.refreshRegisterNumber(tmId);
            });
        }
    }

    public void resetPassword(List<String> userIds,String resetPwd) {
        log.info("update user set password={} where id in {}",resetPwd,userIds);
        userRepository.resetPasswordBatch(userIds,resetPwd);
//        userIds.forEach((String userId) -> {
//            User user = findUserById(userId);
//            user.setPassword(resetPwd);
//            userRepository.saveAndFlush(user);
//        });
    }

    public Page<User> findUserCriteria(Integer page, Integer size, User userQuery,List<String> departIds,String tmId) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "cardNum");
        if (userQuery == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findAll((Specification<User>) (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != userQuery.getId() && !"".equals(userQuery.getId())){
                list.add(cb.equal(root.get("id").as(String.class), userQuery.getId()));
            }
            if(null != userQuery.getCardNum() && !"".equals(userQuery.getCardNum())){
                list.add(cb.equal(root.get("cardNum").as(String.class), userQuery.getCardNum()));
            }
            if(null != userQuery.getName() && !"".equals(userQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + userQuery.getName() + "%"));
            }
            if(null != userQuery.getSex() && !"".equals(userQuery.getSex())){
                list.add(cb.equal(root.get("sex").as(String.class), userQuery.getSex()));
            }
            if(userQuery.getWorkState() != -1){
                list.add(cb.equal(root.get("WorkState").as(Integer.class), userQuery.getWorkState()));
            }
            if(userQuery.getReg_state() != -1){
                Join<User, Terminal> terminalJoin = root.join("terminals", JoinType.LEFT);
                if (userQuery.getReg_state() == 1) {
                    list.add(cb.isNotNull(terminalJoin.get("id").as(String.class)));
                } else if (userQuery.getReg_state() == 0) {
                    list.add(cb.isNull(terminalJoin.get("id").as(String.class)));
                }
            }
            if (null != userQuery.getPost_id() && !"".equals(userQuery.getPost_id()) ||
                    null != departIds && departIds.size() > 0 ||
                    null != userQuery.getRole_id() && !"".equals(userQuery.getRole_id())) {
                Join<User, Post> postJoin = root.join("posts", JoinType.LEFT);
                if (null != userQuery.getPost_id() && !"".equals(userQuery.getPost_id())) {
                    list.add(cb.equal(postJoin.<String>get("id"),userQuery.getPost_id()));
                }
                if (null != departIds && departIds.size() > 0) {
                    list.add(postJoin.<Department>get("department").<String>get("id").in(departIds));
                }
                if (null != userQuery.getRole_id() && !"".equals(userQuery.getRole_id())) {
                    list.add(cb.equal(postJoin.<Role>get("role").<String>get("id"),userQuery.getRole_id()));
                }
            }

            if (null != tmId && !"".equals(tmId)) {
                Join<Object, Object> terminalJoin = root.join("terminals", JoinType.LEFT);
                list.add(cb.equal(terminalJoin.<String>get("id"),tmId));
            }

            Predicate[] p = new Predicate[list.size()];

            return query.where(list.toArray(p)).distinct(true).getRestriction();
        },pageable);
    }

    public List<User> findUserExample(User user) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())//模糊查询匹配
                .withIgnorePaths("id","cardNum","IcNo","password","age","sex","terminalRole","hireDate","leaveDate","WorkState","bornDate","agent","isAttendancer","avatar","template","regTime");//忽略字段
        Example<User> queryExample = Example.of(user,matcher);
        Sort orders = new Sort(Sort.Direction.ASC,"regTime");
        return userRepository.findAll(queryExample,orders);
    }

    public List<User> findUserSpec(User userQuery) {
        Sort sort = new Sort(Sort.Direction.ASC,"regTime");
        return userRepository.findAll((Specification<User>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != userQuery.getName() && !"".equals(userQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + userQuery.getName() + "%"));
            }
            if(null != userQuery.getCardNum() && !"".equals(userQuery.getCardNum())){
                list.add(cb.equal(root.get("cardNum").as(String.class), userQuery.getCardNum()));
            }
            if(null != userQuery.getHireDate()) {
                list.add(cb.greaterThanOrEqualTo(root.get("hireDate").as(String.class), Constant.YMD_FORMAT.format(userQuery.getHireDate())));
            }
            Join<User, Post> postJoin = root.join("posts", JoinType.LEFT);
            if (null != userQuery.getPost_id() && !"".equals(userQuery.getPost_id())) {
                list.add(cb.equal(postJoin.<Integer>get("id"),userQuery.getPost_id()));
            }
            if (null != userQuery.getDepart_id() && !"".equals(userQuery.getDepart_id())) {
                list.add(cb.equal(postJoin.<Department>get("department").<Integer>get("id"),userQuery.getDepart_id()));
            }
            if (null != userQuery.getRole_id() && !"".equals(userQuery.getRole_id())) {
                list.add(cb.equal(postJoin.<Role>get("role").<Integer>get("id"),userQuery.getRole_id()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }, sort);
    }

    @Transactional
    public List<Menu> queryGrantOfUser(String userId) throws Exception {
        User user = findUserById(userId);

        Set<Menu> menuList = new HashSet<>();

        Set<Post> postSet = user.getPosts();
        if (postSet == null || postSet.size() <= 0) {
            throw new ServiceException(ResultCode.FAILED.code,"该用户没有任何权限");
        }

        postSet.forEach((post) -> {
            Set<Menu> menus = post.getMenus();
            menuList.addAll(menus);
        });

        return new ArrayList<>(menuList);
    }

    public User checkAccount(String cardNum,String password) throws ServiceException {
        User isExists = userRepository.findUserByCardNumAndPassword(cardNum, password);
        if (isExists == null) {
            throw new ServiceException(ResultCode.FAILED.code,"用户的账号或密码不正确,请重新输入");
        }
        return isExists;
    }

    @Transactional
    public boolean isExistsUser(String cardNum,String macAddr) {
        User user = userRepository.findUserByCardNum(cardNum);
        return user != null;
    }

    //在原来的基础上给人员分配岗位
    public void allotPost(String userId,String postId) {
        Post post = new Post(postId);
        User user = findUserById(userId);
        user.getPosts().add(post);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void allotPostsByUser(String userId,List<String> postIds) {
        if (postIds == null) {
            throw new ServiceException(ResultCode.FAILED.code,"岗位集合为空");
        }
//        User user = findUserById(userId);
//        user.getPosts().clear();
        userRepository.clearUsersByUserId(userId);
        List<UserPost> userPosts = new ArrayList<>();
        UserPost userPost;
        for (String postId : postIds) {
            userPost = new UserPost(userId,postId);
            userPosts.add(userPost);
        }

        userPostRepository.insertUserPostBatch(userPosts);

    }

    @Transactional
    public void allotPosts(String postId,List<String> userIds) {
        // XXX:该方法会重新分配用户的岗位，不会每次都查用户或岗位是否存在
        userRepository.clearUsersByPostId(postId);
        List<UserPost> userPosts = new ArrayList<>();
        UserPost userPost;
        for (String userId : userIds) {
            userPost = new UserPost(userId,postId);
            userPosts.add(userPost);
        }
//        bitchUpdate(userList);
        userPostRepository.insertUserPostBatch(userPosts);
        log.info("allot user ids:" + userIds + " by post:" + postId);
    }

    @Transactional
    public void register(User user, String macAddr) {
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        if (user.getAgent() != null) {
            User agent = findUserById(user.getAgent().getId());
            user.setAgent(agent);
        }

        Terminal terminal =  findTerminalByMac(macAddr);
        user.addTerminal(terminal);

        //该终端是否开启自动转发
        if (terminal.getTransmitState() == Constant.STATE_ON) {
            //查找允许接受其它终端注册人员的终端
            List<Terminal> acceptOnTmList = terminalRepository.findByAcceptState(Constant.STATE_ON);
            for (Terminal tm : acceptOnTmList) {
                register(user,tm.getMacAddr());
            }
        }
        // 保存用户
        userRepository.saveAndFlush(user);
        // 刷新终端注册人数
        terminalRepository.refreshRegisterNumber(terminal.getId());
    }

    @Transactional
    public void registerBitch(List<User> userList, String macAddr) {
        for (User user : userList) {
            if (StringUtils.isEmpty(user.getId())) {
                user.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
            }
            if (user.getAgent() != null) {
                User agent = findUserById(user.getAgent().getId());
                user.setAgent(agent);
            }

            Terminal terminal =  findTerminalByMac(macAddr);
            boolean addResult = user.addTerminal(terminal);
            if (addResult) {
                //更新该终端的信息
                terminal.incRegister();
                terminalRepository.saveAndFlush(terminal);
            }

            //该终端是否开启自动转发
            if (terminal.getTransmitState() == Constant.STATE_ON) {
                //查找允许接受其它终端注册人员的终端
                List<Terminal> acceptOnTmList = terminalRepository.findByAcceptState(Constant.STATE_ON);
                for (Terminal tm : acceptOnTmList) {
                    if (macAddr.equals(tm.getMacAddr())) {
                        continue;
                    }
                    addResult = user.addTerminal(tm);
                    if (addResult) {
                        //更新该终端的信息
                        tm.incRegister();
                    }
                }
                terminalManager.bitchUpdate(acceptOnTmList);
            }
        }
        bitchInsert(userList);
    }

    @Transactional
    public void updateUserInfo(User user) {
        User oldUser = findUserById(user.getId());
        //如果为int类型，不管数据是否为0，都更新
        oldUser.setWorkState(user.getWorkState());
        oldUser.setIsAttendancer(user.getIsAttendancer());
        if (!StringUtils.isEmpty(user.getCardNum())) {
            oldUser.setCardNum(user.getCardNum());
        }
        if (!StringUtils.isEmpty(user.getName())) {
            oldUser.setName(user.getName());
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            oldUser.setPassword(user.getPassword());
        }
        if (!StringUtils.isEmpty(user.getAvatar())) {
            oldUser.setAvatar(user.getAvatar());
        }
        if (!StringUtils.isEmpty(user.getTemplate())) {
            oldUser.setTemplate(user.getTemplate());
        }

        if (!StringUtils.isEmpty(user.getSex())) {
            oldUser.setSex(user.getSex());
        }
        if (!StringUtils.isEmpty(user.getRegTime())) {
            oldUser.setRegTime(user.getRegTime());
        }
        if (!StringUtils.isEmpty(user.getTerminalRole())) {
            oldUser.setTerminalRole(user.getTerminalRole());
        }
        if (user.getAge() > 0) {
            oldUser.setAge(user.getAge());
        }
        if (!StringUtils.isEmpty(user.getAgent())) {
            oldUser.setAgent(user.getAgent());
        }
        if (!StringUtils.isEmpty(user.getBornDate())) {
            oldUser.setBornDate(user.getBornDate());
        }
        if (!StringUtils.isEmpty(user.getHireDate())) {
            oldUser.setHireDate(user.getHireDate());
        }
        if (!StringUtils.isEmpty(user.getIcNo())) {
            oldUser.setIcNo(user.getIcNo());
        }
        if (!StringUtils.isEmpty(user.getLeaveDate())) {
            oldUser.setLeaveDate(user.getLeaveDate());
        }

        userRepository.saveAndFlush(oldUser);

    }

    @Transactional
    @Deprecated
    public void allotUserList(List<String> userIdList,String tmId) {
        Terminal terminal = findTerminalById(tmId);
        List<User> userList = new ArrayList<>();
        for (String userId : userIdList) {
            User userItem = findUserById(userId);
            userItem.addTerminal(terminal);
            userList.add(userItem);
        }
        bitchUpdate(userList);
        terminalRepository.refreshRegisterNumber(terminal.getId());
    }

    @Transactional
    public void allotUsers(@NotNull String tmId,@NotNull List<String> userIds) {
        // XXX: 本方法会在原来的基础上添加数据
        // 查找该终端已拥有的用户集合
        List<String> existsUserIds = userRepository.findUserIdsByTmId(tmId);
        Map<String,String> existsUserMap = new HashMap<>();
        if (existsUserIds != null && existsUserIds.size() > 0) {
            existsUserIds.forEach(userId -> {
                existsUserMap.put(userId,userId);
            });
            existsUserIds.clear();
        }
        List<UserTerminal> userTerminals = new ArrayList<>();
        UserTerminal userTerminal;
        boolean isExists = true;
        for (String userId : userIds) {
            // 该终端不存在该用户时
            if (existsUserMap.get(userId) == null) {
                isExists = false;
                userTerminal = new UserTerminal(userId,tmId);
                userTerminals.add(userTerminal);
            }

        }

        if (!isExists) {
            // 批量插入数据
//        bitchUpdate(userList);
            userTerminalRepository.insertUserTerminalBatch(userTerminals);
            //跟新终端注册个数
            terminalRepository.refreshRegisterNumber(tmId);
        }

    }

    @Transactional
    public void allotUsers(@NotNull List<String> tmIds,@NotNull List<String> userIds) {
        // XXX: 本方法会在原来的基础上添加数据
        List<UserTerminal> userTerminals = new ArrayList<>();

        tmIds.forEach(tmId -> {
            List<String> existsUserIds = userRepository.findUserIdsByTmId(tmId);
            Map<String,String> existsUserMap = new HashMap<>();
            if (existsUserIds != null && existsUserIds.size() > 0) {
                existsUserIds.forEach(userId -> {
                    existsUserMap.put(userId,userId);
                });
                existsUserIds.clear();
            }
            UserTerminal userTerminal;
            for (String userId : userIds) {
                // 该终端不存在该用户时
                if (existsUserMap.get(userId) == null) {
                    userTerminal = new UserTerminal(userId,tmId);
                    userTerminals.add(userTerminal);
                }

            }
        });


        // 批量插入数据
//        bitchUpdate(userList);
        userTerminalRepository.insertUserTerminalBatch(userTerminals);

        //TODO:批量跟新终端注册个数
        tmIds.forEach(tmId -> {
            terminalRepository.refreshRegisterNumber(tmId);
        });

    }


    public boolean isIncludeTerminals(String userId,List<String> tmIdList) {
        if (tmIdList == null || tmIdList.size() <= 0) {
            return true;
        }
        List<String> hasTmIds = userRepository.findTmIdsByUserId(userId);
        if (hasTmIds == null || hasTmIds.size() <= 0) {
            return false;
        }

        Map<String,String> hasTmIdMap = new HashMap<>();

        hasTmIds.forEach(tmId -> {
            hasTmIdMap.put(tmId,tmId);
        });

        for (String tmId : tmIdList) {
            if (hasTmIdMap.get(tmId) == null) {
                return false;
            }
        }

        return true;
    }

    // 在原来的基础上添加关联终端
    public void allotTerminalList(String userId,List<String> tmIdList) {
        if (tmIdList == null) {
            return;
        }
        User user = findUserById(userId);

        Map<String,String> hasExistsMap = new HashMap<>();

        user.getTerminals().forEach(tm -> {
            hasExistsMap.put(tm.getId(),tm.getMacAddr());
        });
        boolean isExists = true;
        List<UserTerminal> userTerminals = new ArrayList<>();
        UserTerminal userTerminal;
        for (int i = 0;i < tmIdList.size();i++) {
            if (hasExistsMap.get(tmIdList.get(i)) == null) {
                isExists = false;
                userTerminal = new UserTerminal(userId,tmIdList.get(i));
                userTerminals.add(userTerminal);
            } else {
                tmIdList.remove(i);
            }

        }

        if (isExists) {
            throw new ServiceException(ResultCode.SUCCESS.code,"终端已关联该用户");
        }

        userTerminalRepository.insertUserTerminalBatch(userTerminals);
        // TODO:批量更新终端注册人数
        tmIdList.forEach(tmId -> {
            terminalRepository.refreshRegisterNumber(tmId);

        });
        // TODO:下发用户信息到终端新注册的终端

    }


    @Transactional
    public void delUserByMacAddr(String userId,String macAddr,boolean isDelRel,boolean isDelData) {
        Terminal terminal = findTerminalByMac(macAddr);
        delUserByTerminal(userId,terminal.getId(),isDelRel,isDelData);
    }


    @Transactional
    public void delUserByTerminal(String userId,String tmId,boolean isDelRel,boolean isDelData) {
        //是否真正删除该人员
        if (isDelData) {
            userRepository.deleteById(userId);
            return;
        }

        User user = findUserById(userId);
        user.removeTerminal(tmId);

        //是否删除其他终端的该用户信息
        if (isDelRel) {
            List<Terminal> otherTerminals = terminalRepository.findByAcceptState(Constant.STATE_ON);
            for (Terminal tm : otherTerminals) {
                if (tm.getId().equals(tmId)) {
                    continue;
                }
                boolean rmResult = user.removeTerminal(tm.getId());
                if (rmResult) {
                    tm.disRegister();
                    terminalRepository.saveAndFlush(tm);
                }
            }
        }

        userRepository.saveAndFlush(user);
        terminalRepository.refreshRegisterNumber(tmId);

    }

    @Transactional
    @Deprecated
    public void delUsersByTerminalId(List<String> userIdList,String tmId,boolean isDelRel,boolean isDelData) {
        for (String userId : userIdList) {
            delUserByTerminal(userId,tmId,isDelRel,isDelData);
        }
    }

    @Transactional
    @Deprecated
    public void delAllUserByTerminalId(String tmId,boolean isDelRel,boolean isDelData) {
        List<String> userIdList = userRepository.findUserIdsByTerminalId(tmId);
        delUsersByTerminalId(userIdList,tmId,isDelRel,isDelData);
    }

    @Transactional
    public void refreshRegisterNumber(Terminal terminal) {
        userRepository.countUsersByTerminalId(terminal.getId());
        terminalRepository.saveAndFlush(terminal);
    }

    public User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到用户[" + userId + "]"));
    }


    public Terminal findTerminalById(String tmId) {
        Optional<Terminal> terminal = terminalRepository.findById(tmId);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + tmId + "]不存在");
        }
        return terminal.get();
    }

    public Terminal findTerminalByMac(String macAddr) {
        Optional<Terminal> terminal = terminalRepository.findByMacAddr(macAddr);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + macAddr + "]不存在");
        }
        return terminal.get();
    }

    public Post findPostById(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"岗位[" + postId + "]不存在");
        }
        return post.get();
    }

}
