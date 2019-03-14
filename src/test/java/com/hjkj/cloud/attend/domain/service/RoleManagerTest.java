package com.hjkj.cloud.attend.domain.service;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.Role;
import com.hjkj.cloud.attend.domain.repository.IRoleRepository;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class RoleManagerTest {


    @Autowired
    private RoleManager roleManager;

    @Test
    @Transactional
    public void findRoleCriteria() throws Exception {
//        Role queryRole = new Role();
//        queryRole.setId("51fe8ee8");
//        Page<Role> roleList = roleManager.findRoleCriteria(0, 3, queryRole);
        Role role = roleManager.findRoleById("51fe8ee8");
        role.getPosts().forEach((post) -> {
            System.out.println(post.getDepartment().getId());
            System.out.println(post.getDepartment().getName());
            System.out.println();
        });
    }

    @Test
    public void findRoleDetailCriteria() throws Exception {
        Role queryRole = new Role();
        queryRole.setId("6b586af9");
        queryRole.setName("普通用户");
        Page<Role> roleList = roleManager.findRoleCriteria(0, 3, queryRole);
        for (Role role : roleList) {
            //json输出需加事务
            for (Post post : role.getPosts()) {
                System.out.println(post.getName());
                System.out.println(post.getDepartment().getName());
            }
        }
    }

    @Test
    public void saveRole() throws Exception {
        initRoles();
    }

    @Test
    @Transactional
    public void initRoles() {
        Role role1 = new Role();
        role1.setId(StringUtils.genUUID(8));
        role1.setName("普通用户");
        role1.setValue(3);
        roleManager.saveRole(role1);

        Role role2 = new Role();
        role2.setId(StringUtils.genUUID(8));
        role2.setName("普通管理员");
        role2.setValue(2);
        roleManager.saveRole(role2);

        Role role3 = new Role();
        role3.setId(StringUtils.genUUID(8));
        role3.setName("超级管理员");
        role3.setValue(1);
        roleManager.saveRole(role3);

    }

    @Test
    public void delRoleById() throws Exception {
        roleManager.delRoleById("0035b2a4",false);
    }

    @Test
    public void delRolesByIds() throws Exception {
        List<String> roleIds = new ArrayList<>();
        roleIds.add("71a7fb03");
        roleIds.add("b6f4e0d5");
        roleManager.delRolesByIds(roleIds,false);
    }


}