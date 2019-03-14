package com.hjkj.cloud.attend.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.OrganizeService;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.DepartDto;
import com.hjkj.cloud.attend.ui.dto.web.FullDepart;
import com.hjkj.cloud.attend.ui.dto.web.PostDto;
import com.hjkj.cloud.attend.ui.dto.web.RoleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class OrganizeServiceImplTest {

    @Autowired
    private OrganizeService organizeService;

    @Test
    public void queryRoles() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setPage(0);
        roleDto.setSize(5);
        Page<RoleDto> roleDtos = organizeService.queryRoles(roleDto);
        System.out.println(roleDtos.getTotalElements());
        System.out.println(JSON.toJSONString(roleDtos));
    }

    @Test
    public void queryPosts() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setPage(0);
        postDto.setSize(5);
        Page<PostDto> postDtos = organizeService.queryPosts(postDto);
        System.out.println(JSON.toJSONString(postDtos));
    }

    @Test
    public void queryDeparts() throws Exception {
        Page<DepartDto> departDtos = organizeService.queryDeparts(null);
        System.out.println(JSON.toJSONString(departDtos));
    }

    @Test
    public void queryAllFullDepart() throws Exception {
        List<FullDepart> fullDeparts = organizeService.queryFullDeparts(null);
        System.out.println(JSON.toJSONString(fullDeparts));
    }

    @Test
    public void addRole() throws Exception {
    }

    @Test
    public void delRole() throws Exception {
    }

    @Test
    public void delRoles() throws Exception {
    }

    @Test
    public void allotPosts() throws Exception {
    }

    @Test
    public void findPostsByRole() throws Exception {
        List<PostDto> postList = organizeService.findPostsByRole("51fe8ee8");
        for (PostDto postDto : postList) {
            System.out.println(JSON.toJSONString(postDto));
        }
    }

    @Test
    public void findPostByUserId() throws Exception {
        List<PostDto> postList = organizeService.findPostByUserId("35532f7f");
        for (PostDto postDto : postList) {
            System.out.println(JSON.toJSONString(postDto));
        }
    }

    @Test
    public void addPost() throws Exception {
    }

    @Test
    public void addDepartment() throws Exception {
    }

    @Test
    public void registerMenu() throws Exception {
    }

    @Test
    public void registerMenus() throws Exception {
    }

    @Test
    public void delMenu() throws Exception {
    }

    @Test
    public void delMenus() throws Exception {
    }

    @Test
    public void queryMenus() throws Exception {
    }

    @Test
    public void queryUsers() throws Exception {
        UserDto userDto = new UserDto();
        List<UserDto> userDtoList = organizeService.queryUsers(userDto);
        System.out.println(userDtoList.size());
        System.out.println(JSON.toJSONString(userDtoList));
    }

    @Test
    public void queryUsersByPage() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setDepart_id("7faea816");
        userDto.setWork_state(-1);
        userDto.setReg_state(1);
        userDto.setPage(0);
        userDto.setSize(7);
        Page<UserDto> userDtos = organizeService.queryUserPage(userDto);
        System.out.println(userDtos.getTotalElements());
        System.out.println(JSON.toJSONString(userDtos));
    }

    @Test
    public void modifyUserInfo() throws Exception {
    }

    @Test
    public void deleteUserByIds() throws Exception {
    }

    @Test
    public void resetPassword() throws Exception {
    }

    @Test
    public void queryRelateTerminals() throws Exception {
        List<TerminalDto> terminalDtoList = organizeService.queryRelateTerminals("dc28e5f1");
        System.out.println(JSON.toJSONString(terminalDtoList));
    }

}