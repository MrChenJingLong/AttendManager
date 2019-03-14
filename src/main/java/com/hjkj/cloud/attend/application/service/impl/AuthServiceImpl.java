package com.hjkj.cloud.attend.application.service.impl;

import com.hjkj.cloud.attend.application.convert.DomainAssemble;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.domain.model.Menu;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.IMenuRepository;
import com.hjkj.cloud.attend.domain.repository.IPostRepository;
import com.hjkj.cloud.attend.domain.service.PostManager;
import com.hjkj.cloud.attend.domain.service.UserManager;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.utils.DESPlus;
import com.hjkj.cloud.attend.infrastructure.utils.RedisUtil;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.Account;
import com.hjkj.cloud.attend.ui.dto.web.GrantDto;
import com.hjkj.cloud.attend.ui.dto.web.MenuDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 用户权限服务
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Value("${version:V1.1}")
    private String version;

    @Autowired
    private UserManager userManager;
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private PostManager postManager;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String login(Account account) throws Exception {
        User user = userManager.checkAccount(account.getUsername(), account.getPassword());
        //生成token
        String token = DESPlus.encryptDES(String.format("%s%s", user.getName(), System.currentTimeMillis()), com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        //将token保存到redis
        redisUtil.set(token,user.getId(),Constant.EXPIRE_TIME);
        return token;
    }

    @Override
    public void refreshTokenTime(String token) {
        redisUtil.expire(token,Constant.EXPIRE_TIME);
    }

    @Override
    public void logout(String token) {
        //删除token缓存
        if (redisUtil.get(token) != null) {
            redisUtil.del(token);
        }
    }

    @Override
    public List<GrantDto> queryGrantByUser(String userId) throws Exception {
        List<Menu> menuList = userManager.queryGrantOfUser(userId);
        List<MenuDto> menuDtoList = DomainAssemble.copyOfMenuList(menuList);
        return transToGrantList(menuDtoList);
    }

    @Override
    public List<GrantDto> queryGrantByPost(String postId) {
        Post post = postManager.findPostById(postId);
        List<Menu> menuList = new ArrayList<>(post.getMenus());
        List<MenuDto> menuDtoList = DomainAssemble.copyOfMenuList(menuList);
        return transToGrantList(menuDtoList);
    }

    @Override
    public List<String> queryCheckedKeysByPost(String postId) {
        List<String> keys = new ArrayList<>();
        Post post = postManager.findPostById(postId);
        List<Menu> menuList = new ArrayList<>(post.getMenus());
        menuList.forEach(menu -> {
            keys.add(menu.getId());
        });
        return keys;
    }

    @Override
    public List<GrantDto> queryAllGrant() {
        List<GrantDto> grantDtoList;
//        Object res = redisUtil.get(Constant.ATTEND_ALL_MENUS);
//        if (res == null) {
            List<Menu> menuList = menuRepository.findAll();
            List<MenuDto> menuDtoList = DomainAssemble.copyOfMenuList(menuList);
            grantDtoList = transToGrantList(menuDtoList);
            redisUtil.set(Constant.ATTEND_ALL_MENUS,grantDtoList,Constant.EXPIRE_TIME);
//        } else {
//            grantDtoList = (List<GrantDto>) res;
//        }
        return grantDtoList;
    }

    @Override
    public List<GrantDto> queryGrantByToken(String token) throws Exception {
        String userId = getUserIdFromToken(token);
        return queryGrantByUser(userId);
    }

    private static List<GrantDto> transToGrantList(List<MenuDto> menuDtoList) {
        List<GrantDto> grantDtoList = new ArrayList<>();
        if (menuDtoList == null || menuDtoList.size() <= 0) {
            return grantDtoList;
        }

        //排序
        menuDtoList.sort(Comparator.comparingInt(MenuDto::getSort));

        List<MenuDto> secondMenuDtos = new ArrayList<>();
        GrantDto grantDto;
        for (MenuDto menuDto : menuDtoList) {
            if (!StringUtils.isEmpty(menuDto.getParent_id())) {
                secondMenuDtos.add(menuDto);
            }
        }

        for (MenuDto menuDto : menuDtoList) {
            if (StringUtils.isEmpty(menuDto.getParent_id())) {
                grantDto = new GrantDto();
                grantDto.setMenu_id(menuDto.getMenu_id());
                grantDto.setName(menuDto.getName());
                grantDto.setIcon(menuDto.getIcon());
                grantDto.setRef(menuDto.getRef());
                grantDto.setSort(menuDto.getSort());
                grantDto.setType(menuDto.getType());
                grantDto.setUrl(menuDto.getUrl());
                grantDto.setType(menuDto.getType());
                grantDto.setUser_limit(menuDto.getUser_limit());
                grantDto.setHidden(menuDto.isHidden());
                List<GrantDto> children= findNextMenuById(secondMenuDtos,grantDto.getMenu_id());
                grantDto.setChildren(children);
                grantDtoList.add(grantDto);
            }

        }

        return grantDtoList;
    }

    private static List<GrantDto> findNextMenuById(List<MenuDto> MenuDtoList, String menuId) {
        List<GrantDto> menuDtoList = new ArrayList<>();
        if (MenuDtoList == null || MenuDtoList.size() <= 0) {
            return menuDtoList;
        }
        GrantDto grantDto;
        for (int i = 0;i < MenuDtoList.size();i++) {
            if (MenuDtoList.get(i).getParent_id().equals(menuId)) {
                grantDto = new GrantDto();
                grantDto.setMenu_id(MenuDtoList.get(i).getMenu_id());
                grantDto.setName(MenuDtoList.get(i).getName());
                grantDto.setIcon(MenuDtoList.get(i).getIcon());
                grantDto.setRef(MenuDtoList.get(i).getRef());
                grantDto.setSort(MenuDtoList.get(i).getSort());
                grantDto.setType(MenuDtoList.get(i).getType());
                grantDto.setUrl(MenuDtoList.get(i).getUrl());
                grantDto.setType(MenuDtoList.get(i).getType());
                grantDto.setUser_limit(MenuDtoList.get(i).getUser_limit());
                grantDto.setHidden(MenuDtoList.get(i).isHidden());
                grantDto.setChildren(findNextMenuById(MenuDtoList,MenuDtoList.get(i).getMenu_id()));
                menuDtoList.add(grantDto);
            }
        }
        return menuDtoList;
    }

    @Override
    public String getUserIdFromToken(String token) {
        return (String) redisUtil.get(token);
    }

    @Override
    public User getUser(String userId) {
        return userManager.findUserById(userId);
    }

    @Override
    public UserDto getUserByToken(String token) {
        String userId = getUserIdFromToken(token);
        return getUserById(userId);
    }

    @Override
    public UserDto getUserById(String id) {
        User user = getUser(id);
        return DomainAssemble.copyOfUser(user);
    }
}
