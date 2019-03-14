package com.hjkj.cloud.attend.domain.service;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.model.Menu;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class UserManagerTest {


    @Autowired
    private UserManager userManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private TerminalManager terminalManager;

    @Test
    public void saveOrUpdateUser() throws Exception {
        User user = new User();

        Set<Post> posts = new HashSet<>();
        Set<Terminal> terminals = new HashSet<>();

        Post post = new Post();
        post.setId("");
        posts.add(post);

        Terminal terminal1 = new Terminal();
        terminal1.setId("df6f10bf");
        Terminal terminal2 = new Terminal();
        terminal2.setId("24cba43e");
        terminals.add(terminal1);
        terminals.add(terminal2);

        user.setId("dc28e5f1");
        user.setName("test");
        user.setCardNum("1099");
        user.setAge(18);
        user.setSex("男");
        user.setHireDate(new Date());
        user.setBornDate(Constant.YMD_FORMAT.parse("1995-09-12"));
        user.setPassword("123456");
        user.setPosts(posts);
        user.setTerminals(terminals);

        userManager.saveOrUpdateUser(user);
    }

    @Test
    public void deleteUserByIds() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("3e8f4451");
        userManager.deleteUserByIds(userIdList);
    }

    @Test
    public void resetPassword() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("fc99a570");
        userIdList.add("017440ad");
        userManager.resetPassword(userIdList,"123");
    }

    @Test
    public void findUserCriteria() throws Exception {
        User userQuery = new User();
        userQuery.setDepart_id("13361303");
        userQuery.setPost_id("2e7edf36");
        userQuery.setRole_id("6c9ae27b");
        userQuery.setIsAttendancer(-1);
        userQuery.setWorkState(-1);
        Page<User> userPage = userManager.findUserCriteria(0, 3, userQuery,null,null);
        userPage.forEach(user -> {
            System.out.println(user.getId() + ":" + user.getName());
            user.getPosts().forEach(post -> {
                System.out.println(post.getId() + ":" + post.getName() + ":" + post.getDepartment().getName() + ":" + post.getRole().getName());
            });
        });
    }

    @Test
    public void findUserExample() throws Exception {
//        User user = new User();
//        user.setName("");
//        List<User> userList = userManager.findUserExample(user);

//        List<User> userList = userRepository.findUsersByDepartId("fdd966ec");
//        List<User> userList = userRepository.findUsersByRoleId("6c9ae27b");
        List<User> userList = userRepository.findUsersByPostId("2e7edf36");
        userList.forEach(u -> {
            System.out.println(u.getId() + ":" + u.getName());
        });
        System.out.println(userList.size());
    }


    @Test
    @Transactional
    public void queryGrantOfUser() throws Exception {
        User user = new User();
        user.setCardNum("00000002");
        user.setPassword("123456");
        List<Menu> menuList = userManager.queryGrantOfUser("");
        System.out.println(JSON.toJSONString(menuList));
    }

    @Test
    public void isExistsUser() throws Exception {
        boolean isExists = userManager.isExistsUser("08c21690", "00000002");
        Assert.assertTrue("终端[00000002]不存在用户[08c21690]",isExists);
    }

    @Test
    public void isExistsTerminals() throws Exception {
//        List<String> tmIds = userRepository.findTmIdsByUserId("0da8067e");
//        System.out.println(tmIds);
        User user = userManager.findUserById("0da8067e");
        boolean res = user.addTerminal(userManager.findTerminalById("a7719dc8"));
        System.out.println("result:" + res);
    }

    @Test
    public void isIncludeTerminals() throws Exception {
        List<String> tmIds = userRepository.findTmIdsByUserId("fc99a570");
        System.out.println(tmIds);
//        tmIds.add("0u9d");
        boolean includeTerminals = userManager.isIncludeTerminals("fc99a570", tmIds);
        System.out.println("includeTerminals:" + includeTerminals);
    }

    @Test
    public void allotTerminalList() throws Exception {
        List<String> tmIds = userRepository.findTmIdsByUserId("0da8067e");
        userManager.allotTerminalList("0da8067e", tmIds);
    }

    @Test
    public void allotPost() throws Exception {
        userManager.allotPost("35532f7f","2e7edf36");
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "cardNum");
        Page<User> userPage = userRepository.findUsersByTerminalsContainsOrderByRegTime(terminalManager.findTerminalById("a7719dc8"),pageable);
        System.out.println(userPage.getTotalElements());
    }

    @Test
    public void allotPosts() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("017440ad");
        userIdList.add("fd671478");
        userIdList.add("1f1b97ca");
        userIdList.add("4178f7e7");
        userIdList.add("3e8f4451");
        userManager.allotPosts("7f6f292d",userIdList);
    }

    @Test
    @Transactional
    public void register() throws Exception {
        for (int i = 1;i <= 100;i++) {
            User user = new User();
            user.setId(StringUtils.genUUID(8));
            user.setCardNum("0000000" + i);
            user.setName("su");
            user.setPassword("123456");
            user.setRegTime(new Date());
            user.setAvatar("");
            user.setTemplate("");
            user.setIsAttendancer(1);
            user.setHireDate(new Date());
            user.setWorkState(1);
            user.setBornDate(new Date());
            user.setTerminalRole("1001");
            userManager.register(user,"00000001");
        }
    }

    @Test
    @Transactional
    public void registerBitch() throws Exception {
        List<User> userList = new ArrayList<>();
        for (int i = 101;i <= 200;i++) {
            User user = new User();
            user.setId(StringUtils.genUUID(8));
            user.setCardNum("0000000" + i);
            user.setName("admin");
            user.setPassword("123456");
            user.setRegTime(new Date());
            user.setAvatar("");
            user.setTemplate("");
            user.setIsAttendancer(1);
            user.setHireDate(new Date());
            user.setWorkState(1);
            user.setBornDate(new Date());
            user.setTerminalRole("1001");
            userList.add(user);
        }
        userManager.registerBitch(userList,"00000001");
    }

    @Test
    public void allotUserList() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("d2d297e7");
        userManager.allotUserList(userIdList,"a7719dc8");
    }

    @Test
    public void delUserByMacAddr() throws Exception {
        userManager.delUserByMacAddr("79f4680d","00000001",true,true);
    }

    @Test
    public void delUserByTerminalId() throws Exception {
        userManager.delUserByTerminal("79f4680d","c55f2211",false,false);
    }

    @Test
    public void delUsersByTerminalId() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("2404ee3f");
        userIdList.add("4b49e26e");
        userIdList.add("878a3dce");
        userManager.delUsersByTerminalId(userIdList,"c55f2211",false,false);
    }

    @Test
    public void delAllUserByTerminalId() throws Exception {
        userManager.delAllUserByTerminalId("c55f2211",false,false);
    }

}