package com.hjkj.cloud.attend.application.service.impl;

import com.hjkj.cloud.attend.application.convert.DomainAssemble;
import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.domain.repository.IDepartmentRepository;
import com.hjkj.cloud.attend.domain.repository.IRoleRepository;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.domain.service.*;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.*;
import com.hjkj.cloud.attend.application.convert.DtoTransform;
import com.hjkj.cloud.attend.application.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 组织结构服务
 */
@Service
public class OrganizeServiceImpl implements OrganizeService {

    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private PostManager postManager;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private TerminalManager terminalManager;


    @Override
    public Page<RoleDto> queryRoles(RoleDto roleDto) {
        Role roleQuery = DtoTransform.copyOfRoleDto(roleDto);
        Page<Role> rolePage = roleManager.findRoleCriteria(roleDto.getPage(), roleDto.getSize(), roleQuery);
        return DomainAssemble.copyOfRolePage(rolePage);
    }

    @Override
    public List<RoleDto> findRoles(RoleDto roleDto) {
        Role role = DtoTransform.copyOfRoleDto(roleDto);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())//模糊查询匹配
                .withIgnorePaths("sort","value");//忽略字段
        Example<Role> queryExample = Example.of(role,matcher);
        Sort orders = new Sort(Sort.Direction.ASC,"sort");
        List<Role> roleList = roleRepository.findAll(queryExample, orders);
        return DomainAssemble.copyOfRoleList(roleList);
    }

    @Override
    public void addRole(RoleDto roleDto) {
        roleManager.saveRole(DtoTransform.copyOfRoleDto(roleDto));
    }

    @Override
    public void delRole(String roleId) {
        roleManager.delRoleById(roleId,false);
    }

    @Override
    public void delRoles(List<String> roleIdList) {
        roleManager.delRolesByIds(roleIdList,false);
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        roleManager.updateRole(DtoTransform.copyOfRoleDto(roleDto));
    }

    @Override
    public void allotPosts(String roleId,List<String> postIdList) {
        postManager.allotRole(postIdList,roleId);
    }

    @Override
    @Transactional
    public List<PostDto> findPostsByRole(String roleId) {
        List<PostDto> postDtoList = new ArrayList<>();
        Role role = roleManager.findRoleById(roleId);
        Set<Post> posts = role.getPosts();
        posts.forEach((post) -> {
            PostDto postDto = new PostDto();
            postDto.setPost_id(post.getId());
            postDto.setPost_name(post.getName());
            postDto.setRole_id(role.getId());
            postDto.setRole_name(role.getName());
            if (null != post.getDepartment()) {
                postDto.setDepart_name(DomainAssemble.genFullDepartName(post.getDepartment()));
            }
            postDtoList.add(postDto);
        });
        return postDtoList;
    }

    @Override
    @Transactional
    public List<PostDto> findPostByUserId(String userId) {
        List<PostDto> postDtoList = new ArrayList<>();
        User user = userManager.findUserById(userId);
        Set<Post> posts = user.getPosts();
        posts.forEach((post) -> {
            PostDto postDto = new PostDto();
            postDto.setPost_id(post.getId());
            postDto.setPost_name(post.getName());
            if (null != post.getRole()) {
                postDto.setRole_id(post.getRole().getId());
                postDto.setRole_name(post.getRole().getName());
            }
            if (null != post.getDepartment()) {
                postDto.setDepart_name(DomainAssemble.genFullDepartName(post.getDepartment()));
            }
            postDtoList.add(postDto);
        });
        return postDtoList;
    }

    @Override
    public List<PostDto> findPostsByDepart(String departId) {
        List<PostDto> postDtoList = new ArrayList<>();
        if (StringUtils.isEmpty(departId)) {
            List<Post> posts = postManager.findPosts(null);
            posts.forEach((post) -> {
                PostDto postDto = new PostDto();
                postDto.setPost_id(post.getId());
                postDto.setPost_name(post.getName());
                if (post.getDepartment() != null) {
                    postDto.setDepart_id(post.getDepartment().getId());
                    postDto.setDepart_name(post.getDepartment().getName());
                }
                postDtoList.add(postDto);
            });
        } else {
            Department depart = departmentManager.findDepartById(departId);
            Set<Post> posts = depart.getPosts();
            posts.forEach((post) -> {
                PostDto postDto = new PostDto();
                postDto.setPost_id(post.getId());
                postDto.setPost_name(post.getName());
                postDto.setDepart_id(depart.getId());
                postDto.setDepart_name(depart.getName());
                postDtoList.add(postDto);
            });
        }
        return postDtoList;
    }

    @Override
    @Transactional
    public Page<PostDto> queryPosts(PostDto postDto) {
        Post post = DtoTransform.copyOfPostDto(postDto);
        Page<Post> postPage = postManager.findPostCriteria(postDto.getPage(), postDto.getSize(), post);
        return DomainAssemble.copyOfPostPage(postPage);
    }

    @Override
    public void addPost(PostDto postDto) {
        postManager.savePost(DtoTransform.copyOfPostDto(postDto));
    }

    @Override
    public void updatePost(PostDto postDto) {
        postManager.updatePost(DtoTransform.copyOfPostDto(postDto));
    }

    @Override
    public void deletePost(String postId) {
        postManager.deletePostById(postId,false);
    }

    @Override
    public void deletePosts(List<String> postIds) {
        postManager.deletePostBitch(postIds,false);
    }

    @Override
    public void allotUser(String postId, List<String> userIds) {
        userManager.allotPosts(postId,userIds);
    }

    @Override
    public void allotGrant(String postId, List<String> menuIds) {
        menuManager.allotPost(menuIds,postId);
    }

    @Override
    public void addDepartment(DepartDto departDto) {
        departmentManager.saveDepartment(DtoTransform.copyOfDepartmentDto(departDto));
    }

    @Override
    public void updateDepartment(DepartDto departDto) {
        departmentManager.updateDepartment(DtoTransform.copyOfDepartmentDto(departDto));
    }

    @Override
    public void deleteDepartment(String departId) {
        departmentManager.deleteFullDepart(departId,false);
    }

    @Override
    public Page<DepartDto> queryDeparts(DepartDto departDto) {
        if (departDto == null) {
            List<Department> departmentList = departmentRepository.findAll();
            return new PageImpl<>(DomainAssemble.copyOfDepartments(departmentList));
        }
        Department departQuery = DtoTransform.copyOfDepartmentDto(departDto);
        Page<Department> departPage = departmentManager.findDepartCriteria(departDto.getPage(), departDto.getSize(), departQuery);
        return DomainAssemble.copyOfDepartPage(departPage);
    }

    @Override
    public List<FullDepart> queryFullDeparts(DepartDto departDto) {
        Department department = DtoTransform.copyOfDepartmentDto(departDto);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())//模糊查询匹配
                .withIgnorePaths("level","is_private","sort","flag");//忽略字段
        Example<Department> queryExample = Example.of(department,matcher);
        Sort orders = new Sort(Sort.Direction.ASC,"sort");
        List<Department> departmentList = departmentRepository.findAll(queryExample,orders);
        List<DepartDto> departDtoList = DomainAssemble.copyOfDepartments(departmentList);
        return transToFullDeparts(departDtoList);
    }

    @Override
    public List<DepartDto> queryDepartDtos(DepartDto departDto) {
        Department department = DtoTransform.copyOfDepartmentDto(departDto);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())//模糊查询匹配
                .withIgnorePaths("level","is_private","sort","flag");//忽略字段
        Example<Department> queryExample = Example.of(department,matcher);
        Sort orders = new Sort(Sort.Direction.ASC,"sort");
        List<Department> departmentList = departmentRepository.findAll(queryExample,orders);
        List<DepartDto> departDtoList = DomainAssemble.copyOfDepartments(departmentList);
        departDtoList.sort(Comparator.comparingInt(DepartDto::getSort));
        return departDtoList;
    }

    private static List<FullDepart> transToFullDeparts(List<DepartDto> departDtoList) {
        List<FullDepart> fullDepartList = new ArrayList<>();
        if (departDtoList == null) {
            return fullDepartList;
        }

        //排序
        departDtoList.sort(Comparator.comparingInt(DepartDto::getSort));

        List<DepartDto> firstDepartList = new ArrayList<>();
        List<DepartDto> subDepartList = new ArrayList<>();

        departDtoList.forEach(departDto -> {
            if (StringUtils.isEmpty(departDto.getParent_id())) {
                firstDepartList.add(departDto);
            } else {
                subDepartList.add(departDto);
            }
        });

        firstDepartList.forEach(departDto -> {
            if (StringUtils.isEmpty(departDto.getParent_id())) {
                FullDepart fullDepart = covertSingleDepart(departDto);
                fullDepart.setChildren(genChildrenDeparts(departDto.getDepart_id(),subDepartList));
                fullDepartList.add(fullDepart);
            }
        });

        return fullDepartList;
    }

    private static FullDepart covertSingleDepart(DepartDto departDto) {
        FullDepart fullDepart = new FullDepart();
        fullDepart.setDepart_id(departDto.getDepart_id());
        fullDepart.setDepart_name(departDto.getDepart_name());
        fullDepart.setFlag(departDto.getFlag());
        fullDepart.setIs_private(departDto.getIs_private());
        fullDepart.setLevel(departDto.getLevel());
        fullDepart.setSort(departDto.getSort());
        fullDepart.setTag(departDto.getTag());
        return fullDepart;
    }

    private static List<FullDepart> genChildrenDeparts(String departId,List<DepartDto> departDtoList) {
        List<FullDepart> fullDepartList = new ArrayList<>();
        departDtoList.forEach(departDto -> {
            if (departId.equals(departDto.getParent_id())) {
                FullDepart fullDepart = covertSingleDepart(departDto);
                fullDepart.setChildren(genChildrenDeparts(departDto.getDepart_id(),departDtoList));
                fullDepartList.add(fullDepart);
            }
        });
        return fullDepartList;
    }

    @Override
    public void registerMenu(MenuDto menuDto) {
        menuManager.saveMenu(DtoTransform.copyOfMenuDto(menuDto));
    }

    @Override
    public void registerMenus(List<MenuDto> menuDtoList) {
        for (MenuDto menuDto : menuDtoList) {
            menuManager.saveMenu(DtoTransform.copyOfMenuDto(menuDto));
        }
    }

    @Override
    public void delMenu(String menuId) {
        menuManager.deleteMenuById(menuId);
    }

    @Override
    public void delMenus(List<String> menuIdList) {
        menuManager.delMenusByIds(menuIdList);
    }

    @Override
    public Page<MenuDto> queryMenus(MenuDto menuDto) {
        Menu menu = DtoTransform.copyOfMenuDto(menuDto);
        Page<Menu> menuPage = menuManager.findMenuCriteria(menuDto.getPage(), menuDto.getSize(), menu);
        return DomainAssemble.copyOfMenuPage(menuPage);
    }

    @Override
    public List<UserDto> queryUsers(UserDto userDto) {
        User user = DtoTransform.copyOfUserDto(userDto);
        List<User> userList = userManager.findUserSpec(user);
        return DomainAssemble.copyOfSimpleUserList(userList);
    }

    @Override
    @Transactional
    public Page<UserDto> queryUserPage(UserDto userDto) {
        User user = DtoTransform.copyOfUserDto(userDto);
        List<String> departIds = null;
        if (null != user.getDepart_id() && !"".equals(user.getDepart_id())) {
            departIds = departmentManager.containsDepartIds(user.getDepart_id());
        }
        Page<User> userPage = userManager.findUserCriteria(userDto.getPage(), userDto.getSize(), user,departIds,userDto.getTm_id());
        return DomainAssemble.copyOfUserPage(userPage);
    }

    @Override
    public void modifyUserInfo(UserDto userDto) {
        User user = DtoTransform.copyOfUserDto(userDto);
        userManager.saveOrUpdateUser(user);
    }

    @Override
    public void deleteUserByIds(List<String> userIds) {
        userManager.deleteUserByIds(userIds);
    }

    @Override
    public String resetPassword(List<String> userIds) {
        userManager.resetPassword(userIds, Constant.DEFAULT_PASSWORD);
        return Constant.DEFAULT_PASSWORD;
    }

    @Override
    public List<TerminalDto> queryRelateTerminals(String userId) {
        User user = userManager.findUserById(userId);
        return DomainAssemble.copyOfTerminalList(new ArrayList<>(user.getTerminals()));
    }

    @Override
    public void allotPostsByUser(String userId, List<String> postIdList) {
        userManager.allotPostsByUser(userId,postIdList);
    }


    @Override
    public void allotTerminals(String userId, List<String> tmIds) {
        userManager.allotTerminalList(userId, tmIds);
    }

}
