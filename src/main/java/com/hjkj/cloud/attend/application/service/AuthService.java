package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.Account;
import com.hjkj.cloud.attend.ui.dto.web.GrantDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthService {


    /**
     * 登录系统
     */
    String login(Account account) throws Exception;

    /**
     * 登出系统
     */
    void logout(String token);

    /**
     * 查询用户权限
     */
    List<GrantDto> queryGrantByUser(String userId) throws Exception;

    /**
     * 查询岗位权限
     */
    List<GrantDto> queryGrantByPost(String postId);

    /**
     * 根据查询权限ID集合
     */
    List<String> queryCheckedKeysByPost(String postId);

    /**
     * 查询所有权限
     */
    List<GrantDto> queryAllGrant();

    /**
     * 查询用户权限
     */
    List<GrantDto> queryGrantByToken(String token) throws Exception;

    /**
     * 根据token获取用户ID
     */
    String getUserIdFromToken(String token);


    /**
     * 获取用户信息
     */
    User getUser(String userId);

    /**
     * 根据token获取用户信息
     */
    UserDto getUserByToken(String token);

    /**
     * 根据id获取用户信息
     */
    UserDto getUserById(String id);

    /**
     * 更新token的过期时间
     */
    void refreshTokenTime(String token);

}
