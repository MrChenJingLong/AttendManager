package com.hjkj.cloud.attend.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.application.service.OrganizeService;
import com.hjkj.cloud.attend.application.service.TerminalService;
import com.hjkj.cloud.attend.domain.model.Role;
import com.hjkj.cloud.attend.domain.service.RoleManager;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.ui.dto.ReqMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.*;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@JLApiParam(value = "com.hjkj.cloud.attend.ui.dto.ReqMsg",type = PropType.JSON)
public class OrganizeController {

    private final Logger log = LoggerFactory.getLogger(OrganizeController.class);

    @Autowired
    private OrganizeService organizeService;
    @Autowired
    private AuthService authService;
    @Autowired
    private TerminalService terminalService;

    //分页查询角色
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/org/query-roles")
    @Auth(posts = {"经理","老板"})
    public RetMsg queryRoles(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        RoleDto roleDto = reqMsg.getContentObject(RoleDto.class);
        Page<RoleDto> roleDtos = organizeService.queryRoles(roleDto);
        return RetMsgGenerator.genSuccessRetMsg("分页获取角色:SUCCESS",roleDtos);
    }

    //按条件查询角色
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/org/find-roles")
    @Auth(posts = {"经理","老板"})
    public RetMsg findRoles(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        RoleDto roleDto = reqMsg.getContentObject(RoleDto.class);
        List<RoleDto> roleList = organizeService.findRoles(roleDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roles",roleList);
        return RetMsgGenerator.genSuccessRetMsg("分页获取角色:SUCCESS",jsonObject);
    }

    //新增角色
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/org/add-role")
    @Auth(posts = {"老板"})
    public RetMsg addRole(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        RoleDto roleDto = reqMsg.getContentObject(RoleDto.class);
        organizeService.addRole(roleDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //删除角色
    @JLApiParam(name = "content",value = "{\"role_id\":\"角色Id\"}",type = PropType.STRING)
    @RequestMapping("/attend/org/del-role")
    @Auth(posts = {"老板"})
    public RetMsg delRole(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String role_id = JSON.parseObject(reqMsg.getContent()).getString("role_id");
        organizeService.delRole(role_id);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //批量删除角色
    @JLApiParam(name = "content",value = "{\"role_ids\":[\"角色Id1\",\"角色Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/org/del-roles")
    @Auth(posts = {"老板"})
    public RetMsg delRoles(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        List<String> roleIdList = JSON.parseObject(reqMsg.getContent()).getJSONArray("role_ids").toJavaList(String.class);
        organizeService.delRoles(roleIdList);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //修改角色
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/org/update-role")
    @Auth(posts = {"老板"})
    public RetMsg updateRole(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        RoleDto roleDto = reqMsg.getContentObject(RoleDto.class);
        organizeService.updateRole(roleDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //查询角色岗位
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/org/query-posts-by-role")
    @Auth(posts = {"经理","老板"})
    public RetMsg queryPostsByRoleId(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String roleId = JSON.parseObject(reqMsg.getContent()).getString("role_id");
        List<PostDto> postDtoList = organizeService.findPostsByRole(roleId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("posts",postDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询角色岗位",jsonObject);
    }

    //查询用户岗位
    @JLApiParam(name = "content",value = "{\"user_id\":\"用户Id\"}",type = PropType.STRING)
    @RequestMapping("/attend/org/query-posts-by-user")
    @Auth
    public RetMsg queryPostsByUserId(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String userId = JSON.parseObject(reqMsg.getContent()).getString("user_id");
        List<PostDto> postDtoList = organizeService.findPostByUserId(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("posts",postDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询角色岗位",jsonObject);
    }

    //分页查询岗位
    @JLApiParam
    @RequestMapping("/attend/org/query-posts")
    @Auth(posts = {"经理","老板"})
    public RetMsg queryPosts(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        PostDto postDto = reqMsg.getContentObject(PostDto.class);
        Page<PostDto> postDtos = organizeService.queryPosts(postDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("posts",postDtos);
        return RetMsgGenerator.genSuccessRetMsg("查询角色岗位",jsonObject);
    }

    //根据部门ID查询岗位集合
    @JLApiParam(name = "content",value = "{\"depart_id\":\"部门Id\"}",type = PropType.STRING)
    @RequestMapping("/attend/org/find-posts-by-depart")
    @Auth(posts = {"经理","老板"})
    public RetMsg findPostsByDepart(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String departId = JSON.parseObject(reqMsg.getContent()).getString("depart_id");
        List<PostDto> postDtos = organizeService.findPostsByDepart(departId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("posts",postDtos);
        return RetMsgGenerator.genSuccessRetMsg("查询角色岗位",jsonObject);
    }

    //分配岗位
    @JLApiParam(name = "content",value = "{\"role_id\":\"角色Id\",\"post_ids\":[\"岗位Id1\",\"岗位Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/org/allot-posts")
    @Auth(posts = {"老板"})
    public RetMsg allotPosts(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONObject jsonObject = JSON.parseObject(reqMsg.getContent());
        List<String> postIdList = jsonObject.getJSONArray("post_ids").toJavaList(String.class);
        String roleId = jsonObject.getString("role_id");
        organizeService.allotPosts(roleId,postIdList);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //新增岗位
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.PostDto",type = PropType.JSON)
    @RequestMapping("/attend/org/add-post")
    @Auth(posts = {"老板"})
    public RetMsg addPost(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        PostDto postDto = reqMsg.getContentObject(PostDto.class);
        organizeService.addPost(postDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //修改岗位
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.PostDto",type = PropType.JSON)
    @RequestMapping("/attend/org/update-post")
    @Auth(posts = {"老板"})
    public RetMsg updatePost(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        PostDto postDto = reqMsg.getContentObject(PostDto.class);
        organizeService.updatePost(postDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //删除岗位
    @JLApiParam(name = "content",value = "{\"post_id\":\"岗位Id\"}",type = PropType.JSON)
    @RequestMapping("/attend/org/delete-post")
    @Auth(posts = {"老板"})
    public RetMsg deletePost(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String postId = JSON.parseObject(reqMsg.getContent()).getString("post_id");
        organizeService.deletePost(postId);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //批量删除岗位
    @JLApiParam(name = "content",value = "{\"post_ids\":[\"岗位Id\",\"岗位Id\"]}",type = PropType.JSON)
    @RequestMapping("/attend/org/delete-posts")
    @Auth(posts = {"老板"})
    public RetMsg deletePosts(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        List<String> postIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("post_ids").toJavaList(String.class);
        organizeService.deletePosts(postIds);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //分配人员
    @JLApiParam(name = "content",value = "{\"post_id\":\"岗位Id\",\"user_ids\":[\"用户Id\",\"用户Id\"]}",type = PropType.JSON)
    @RequestMapping("/attend/org/allot-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg allotUsers(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONObject jsonObject = JSON.parseObject(reqMsg.getContent());
        String postId = jsonObject.getString("post_id");
        List<String> userIds = jsonObject.getJSONArray("user_ids").toJavaList(String.class);
        organizeService.allotUser(postId,userIds);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //获取所有权限
    @GetMapping("/attend/org/get-all-grant")
    @Auth(posts = {"老板"})
    public RetMsg queryAllGrant() {
        List<GrantDto> grantDtoList = authService.queryAllGrant();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menus",grantDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询所有的菜单权限",jsonObject);
    }

    //获取岗位权限
    @JLApiParam(name = "content",value = "{\"post_id\":\"岗位Id\"}",type = PropType.JSON)
    @RequestMapping("/attend/org/query-grant-by-post")
    @Auth(posts = {"老板"})
    public RetMsg queryGrantByPost(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String postId = JSON.parseObject(reqMsg.getContent()).getString("post_id");
        List<String> keys = authService.queryCheckedKeysByPost(postId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keys",keys);
        log.info("postId:" + postId +" => keys:" + jsonObject.toJSONString());
        return RetMsgGenerator.genSuccessRetMsg("查询岗位权限",jsonObject);
    }

    //分配权限
    @JLApiParam(name = "content",value = "{\"post_id\":\"岗位Id\",\"menu_ids\":[\"菜单Id\",\"菜单Id\"]}",type = PropType.JSON)
    @RequestMapping("/attend/org/allot-grant")
    @Auth(posts = {"老板"})
    public RetMsg allotGrant(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONObject jsonObject = JSON.parseObject(reqMsg.getContent());
        String postId = jsonObject.getString("post_id");
        List<String> menuIds = jsonObject.getJSONArray("menu_ids").toJavaList(String.class);
        log.info("postId:" + postId + " allot grants:" + menuIds);
        organizeService.allotGrant(postId,menuIds);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //新增部门
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.DepartDto",type = PropType.JSON)
    @RequestMapping("/attend/org/add-department")
    @Auth(posts = {"老板"})
    public RetMsg addDepartment(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        DepartDto departDto = reqMsg.getContentObject(DepartDto.class);
        organizeService.addDepartment(departDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //跟新部门
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.DepartDto",type = PropType.JSON)
    @RequestMapping("/attend/org/update-department")
    @Auth(posts = {"老板"})
    public RetMsg updateDepartment(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        DepartDto departDto = reqMsg.getContentObject(DepartDto.class);
        organizeService.updateDepartment(departDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //删除部门
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.DepartDto",type = PropType.JSON)
    @RequestMapping("/attend/org/delete-department")
    @Auth(posts = {"老板"})
    public RetMsg deleteDepartment(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        DepartDto departDto = reqMsg.getContentObject(DepartDto.class);
        organizeService.deleteDepartment(departDto.getDepart_id());
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //查询部门
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.DepartDto",type = PropType.JSON)
    @RequestMapping("/attend/org/query-full-departs")
    @Auth
    public RetMsg queryFullDeparts(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        DepartDto departDto = reqMsg.getContentObject(DepartDto.class);
//        List<FullDepart> fullDeparts = organizeService.queryFullDeparts(departDto);
        List<DepartDto> departDtoList = organizeService.queryDepartDtos(departDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("departs",departDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询部门信息成功",jsonObject);
    }

    //按条件查询用户
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    @RequestMapping("/attend/org/query-users")
    @Auth
    public RetMsg queryUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        UserDto userDto = reqMsg.getContentObject(UserDto.class);
        List<UserDto> userDtoList = organizeService.queryUsers(userDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users",userDtoList);
        log.info("users:" + jsonObject.toJSONString());
        return RetMsgGenerator.genSuccessRetMsg("用户查询成功",jsonObject);
    }

    //按条件分页查询用户
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    @RequestMapping("/attend/org/get-users")
    @Auth
    public RetMsg getUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        UserDto userDto = reqMsg.getContentObject(UserDto.class);
        Page<UserDto> userDtos = organizeService.queryUserPage(userDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users",userDtos);
        log.info("users:" + jsonObject.toJSONString());
        return RetMsgGenerator.genSuccessRetMsg("用户查询成功",jsonObject);
    }

    //新增或修改用户
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    @RequestMapping("/attend/org/modify-user")
    @Auth(posts = {"经理","老板"})
    public RetMsg modifyUser(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        UserDto userDto = reqMsg.getContentObject(UserDto.class);
        organizeService.modifyUserInfo(userDto);
        return RetMsgGenerator.genSuccessRetMsg("新增用户成功");
    }

    //批量删除用户
    @JLApiParam(name = "content",value = "{\"user_ids:[\"用户ID\"]\"}",type = PropType.JSON)
    @RequestMapping("/attend/org/delete-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg deleteUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONArray userIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("user_ids");
        organizeService.deleteUserByIds(userIds.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("用户删除成功");
    }

    //批量重置用户密码
    @JLApiParam(name = "content",value = "{\"user_ids\":[\"用户ID\"]}",type = PropType.JSON)
    @RequestMapping("/attend/org/reset-pwds")
    @Auth(posts = {"经理","老板"})
    public RetMsg resetPwds(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONArray userIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("user_ids");
        String defaultPwd = organizeService.resetPassword(userIds.toJavaList(String.class));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("default_pwd",defaultPwd);
        return RetMsgGenerator.genSuccessRetMsg("用户密码重置成功",jsonObject);
    }

    //分配岗位
    @JLApiParam(name = "content",value = "{\"user_id\":\"用户Id\",\"post_ids\":[\"岗位Id1\",\"岗位Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/org/allot-posts-by-user")
    @Auth(posts = {"经理","老板"})
    public RetMsg allotPostsByUser(HttpServletRequest request,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONObject jsonObject = JSON.parseObject(reqMsg.getContent());
        List<String> postIdList = jsonObject.getJSONArray("post_ids").toJavaList(String.class);
        String userId = jsonObject.getString("user_id");
        organizeService.allotPostsByUser(userId,postIdList);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    //查询用户关联终端
    @JLApiParam(name = "content",value = "{\"user_id\":\"用户ID\"}",type = PropType.STRING)
    @RequestMapping("/attend/org/query-terminal-by-user")
    @Auth(posts = {"经理","老板"})
    public RetMsg queryRelateTerminals(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String userId = JSON.parseObject(reqMsg.getContent()).getString("user_id");
        List<TerminalDto> terminalDtoList = organizeService.queryRelateTerminals(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("terminals",terminalDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询用户关联终端",jsonObject);
    }

    //分页查询终端信息
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto",type = PropType.JSON)
    @RequestMapping("/attend/org/query-terminals")
    @Auth(posts = {"经理","老板"})
    public RetMsg queryTerminals(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        TerminalDto terminalDto = reqMsg.getContentObject(TerminalDto.class);
        Page<TerminalDto> terminalDtos = terminalService.queryTerminals(terminalDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("terminals",terminalDtos);
        return RetMsgGenerator.genSuccessRetMsg("终端信息查询成功",jsonObject);
    }

    //用户关联终端
    @JLApiParam(name = "content",value = "{\"user_id\":\"用户Id\",\"terminal_ids\":[\"终端Id1\",\"终端Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/org/allot-terminals")
    @Auth(posts = {"经理","老板"})
    public RetMsg allotTerminals(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String userId = JSON.parseObject(reqMsg.getContent()).getString("user_id");
        JSONArray terminal_ids = JSON.parseObject(reqMsg.getContent()).getJSONArray("terminal_ids");
        organizeService.allotTerminals(userId,terminal_ids.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("用户关联终端成功");
    }

}
