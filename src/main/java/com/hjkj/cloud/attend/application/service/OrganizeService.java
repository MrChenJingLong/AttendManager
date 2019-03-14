package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizeService {

    /**
     * 分页查询角色
     */
    Page<RoleDto> queryRoles(RoleDto roleDto);

    /**
     * 根据条件查询角色
     */
    List<RoleDto> findRoles(RoleDto roleDto);

    /**
     * 新增角色
     */
    void addRole(RoleDto roleDto);

    /**
     * 删除角色
     */
    void delRole(String roleId);

    /**
     * 批量删除角色
     */
    void delRoles(List<String> roleIdList);

    /**
     * 更新角色
     */
    void updateRole(RoleDto roleDto);

    /**
     * 分页查询岗位
     */
    Page<PostDto> queryPosts(PostDto postDto);

    /**
     * 批量分配岗位
     */
    void allotPosts(String roleId, List<String> postIdList);

    /**
     * 查询分配的岗位
     */
    List<PostDto> findPostsByRole(String roleId);

    /**
     * 查询分配的岗位
     */
    List<PostDto> findPostByUserId(String userId);

    /**
     * 查询分配的岗位
     */
    List<PostDto> findPostsByDepart(String departId);

    /**
     * 新增岗位
     */
    void addPost(PostDto postDto);

    /**
     * 修改岗位
     */
    void updatePost(PostDto postDto);

    /**
     * 删除岗位
     */
    void deletePost(String postId);

    /**
     * 批量删除岗位
     */
    void deletePosts(List<String> postIds);

    /**
     * 分配人员
     */
    void allotUser(String postId,List<String> userIds);

    /**
     * 分配权限
     */
    void allotGrant(String postId,List<String> menuIds);

    /**
     * 新增部门
     */
    void addDepartment(DepartDto departDto);

    /**
     * 修改部门
     */
    void updateDepartment(DepartDto departDto);

    /**
     * 删除部门
     */
    void deleteDepartment(String departId);

    /**
     * 分页查询部门
     */
    Page<DepartDto> queryDeparts(DepartDto departDto);

    /**
     * 按条件查询部门
     */
    List<FullDepart> queryFullDeparts(DepartDto departDto);

    /**
     * 按条件查询部门
     */
    List<DepartDto> queryDepartDtos(DepartDto departDto);

    /**
     * 注册菜单
     */
    void registerMenu(MenuDto menuDto);

    /**
     * 批量注册菜单
     */
    void registerMenus(List<MenuDto> menuDtoList);

    /**
     * 根据Id删除菜单
     */
    void delMenu(String menuId);

    /**
     * 批量删除菜单
     */
    void delMenus(List<String> menuIdList);

    /**
     * 查询菜单
     */
    Page<MenuDto> queryMenus(MenuDto menuDto);

    /**
     * 条件查询用户
     */
    List<UserDto> queryUsers(UserDto userDto);

    /**
     * 条件查询用户
     */
    Page<UserDto> queryUserPage(UserDto userDto);

    /**
     * 添加用户
     */
    void modifyUserInfo(UserDto userDto);

    /**
     * 删除用户
     */
    void deleteUserByIds(List<String> userIds);

    /**
     * 重置密码
     */
    String resetPassword(List<String> userIds);

    /**
     * 查询用户管理的终端
     */
    List<TerminalDto> queryRelateTerminals(String userId);

    /**
     * 给用户分配岗位集合
     */
    void allotPostsByUser(String userId,List<String> postIdList);

    /**
     * 用户关联终端
     */
    void allotTerminals(String userId,List<String> tmIds);


}
