package com.hjkj.cloud.attend.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.ui.dto.web.GrantDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class AuthServiceImplTest {

    @Autowired
    private AuthService userService;

    @Test
    public void queryGrantByUser() throws Exception {
        List<GrantDto> grantDtoList = userService.queryGrantByUser("dc28e5f1");
        System.out.println(JSON.toJSONString(grantDtoList));
    }

    @Test
    public void queryGrantByPost() throws Exception {
        List<GrantDto> grantDtoList = userService.queryGrantByPost("69afafcc");
        System.out.println(JSON.toJSONString(grantDtoList));
    }

    @Test
    public void queryAllGrant() throws Exception {
        List<GrantDto> grantDtoList = userService.queryAllGrant();
        System.out.println(JSON.toJSONString(grantDtoList));
    }

}