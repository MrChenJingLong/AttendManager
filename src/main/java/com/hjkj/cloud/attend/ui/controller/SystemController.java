package com.hjkj.cloud.attend.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.infrastructure.cache.UserSession;
import com.hjkj.cloud.attend.infrastructure.cache.UserState;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.utils.CookieUtils;
import com.hjkj.cloud.attend.ui.dto.ReqMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.Account;
import com.hjkj.cloud.attend.ui.dto.web.GrantDto;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class SystemController {

    @Value("${version:V1.1}")
    private String version;

    @Autowired
    private AuthService userService;

    //用户登录
    @JLApiParam(value = "com.hjkj.cloud.attend.ui.dto.web.ReqMsg",type = PropType.JSON)
    @RequestMapping("/attend/sys/sign-in")
    public RetMsg signIn(HttpServletRequest request,@RequestBody String body) throws Exception {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        Account account = reqMsg.getContentObject(Account.class);
        String token = userService.login(account);
        return new RetMsg.Builder(ResultCode.SUCCESS.code,"登录成功").setToken(token).build();
    }

    @JLApiParam
    @GetMapping("/attend/sys/get-user")
    public RetMsg getUsersTate(String token) throws Exception {
        UserDto userInfo = userService.getUserByToken(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_info",userInfo);
        return RetMsgGenerator.genSuccessRetMsg("获取用户数据",jsonObject);
    }

    //查询用户菜单权限
    @JLApiParam
    @GetMapping("/attend/user/query-grant")
    @Auth
    public RetMsg queryGrant(String token) throws Exception {
        List<GrantDto> grantDtoList = userService.queryGrantByToken(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menus",grantDtoList);
        return RetMsgGenerator.genSuccessRetMsg("查询用户菜单权限",jsonObject);
    }

    //用户登出
    @JLApiParam
    @RequestMapping("/attend/sys/sign-out")
    public RetMsg signOut(HttpServletRequest request,HttpServletResponse response,@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        userService.logout(reqMsg.getToken());
        return RetMsgGenerator.genSuccessRetMsg("登出成功");
    }


}
