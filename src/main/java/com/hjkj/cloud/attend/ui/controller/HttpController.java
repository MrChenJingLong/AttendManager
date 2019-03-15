package com.hjkj.cloud.attend.ui.controller;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.IAttendService;
import com.hjkj.cloud.attend.ui.dto.ReqMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import com.hjkj.cloud.attend.ui.dto.web.ClockDto;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: CHEN
 * @Date: 2019/3/14 14:12
 * @Description:
 */
@RestController
public class HttpController {

    private final Logger log = LoggerFactory.getLogger(HttpController.class);


    @Autowired
    IAttendService iAttendService;

    @JLApiParam
    @RequestMapping("/attend/classes/create")
    public RetMsg addClasses(@RequestBody String body){
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        ClassesDto classesDto = reqMsg.getContentObject(ClassesDto.class);
        iAttendService.addClssesDto(classesDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }

    @JLApiParam
    @RequestMapping("/attend/clock/create")
    public RetMsg addClock(@RequestBody String body){
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        ClockDto clockDto = reqMsg.getContentObject(ClockDto.class);
        iAttendService.addClockDto(clockDto);
        return RetMsgGenerator.genSuccessRetMsg();
    }
}
