package com.hjkj.cloud.attend.ui.controller;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.application.service.IAttendService;
import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.ui.dto.ReqMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.QueryDuty;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@JLApiParam(value = "com.hjkj.cloud.attend.ui.dto.ReqMsg",type = PropType.JSON)
public class MyAttendController {

    @Autowired
    private AuthService authService;
    @Autowired
    private IAttendService attendService;

    // 分页查询用户考勤记录
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.web.RoleDto",type = PropType.JSON)
    @RequestMapping("/attend/my/query-dutys")
    @Auth
    public RetMsg queryUserDutys(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String token = reqMsg.getToken();
        UserDto userDto = authService.getUserByToken(token);
        QueryDuty queryDuty = reqMsg.getContentObject(QueryDuty.class);
        queryDuty.setUser_card_num(userDto.getCard_num());
        Page<Duty> dutyDtos = attendService.queryDutyInfo(queryDuty);
        return RetMsgGenerator.genSuccessRetMsg("分页查询用户考勤记录",dutyDtos);
    }

}
