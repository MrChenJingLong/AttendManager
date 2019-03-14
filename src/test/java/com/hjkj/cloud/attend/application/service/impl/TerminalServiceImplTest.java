package com.hjkj.cloud.attend.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.TerminalService;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing //更新时间自动赋值
public class TerminalServiceImplTest {

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void registry() throws Exception {
        TerminalDto terminalDto = new TerminalDto();
        terminalDto.setId("ab90f10e");
        terminalDto.setAutoTrans(true);
        terminalDto.setAutoAccept(true);
        terminalDto.setStatus(0);
        terminalDto.setName("终端[201903071642]");
        terminalDto.setVersion("V1.1.2");
        terminalDto.setUsag_cap(0);
        terminalDto.setTotal_cap(100);
        terminalDto.setStra_records(0);
        terminalDto.setReg_user_num(0);
        terminalDto.setComp_records(0);
        terminalDto.setAllow_user_num(1000);
        terminalDto.setReg_time(Constant.SIMPLE_DATE_FORMAT.format(new Date()));
        terminalDto.setIp("192.168.10.47");
        terminalDto.setPort(8760);
        terminalDto.setMac_addr("00000010");
        terminalService.registry(terminalDto);
    }

    @Test
    public void deleteTerminals() throws Exception {
        List<String> tmIdList = new ArrayList<>();
        tmIdList.add("ab90f10e");
        terminalService.deleteTerminals(tmIdList);
    }

    @Test
    public void queryUsersOfTerminal() throws Exception {
        Page<UserDto> userDtoPage = terminalService.queryUsersOfTerminal("a7719dc8", 0, 5);
        System.out.println(JSON.toJSONString(userDtoPage));
    }

    @Test
    public void allotUsers() throws Exception {
        List<String> tmIds = new ArrayList<>();
        tmIds.add("ab90f10e");
        List<String> userIds = userRepository.findUserIdsByTerminalId("a7719dc8");
        System.out.println("terminal[a7719dc8] allot user size:" + userIds.size());
        terminalService.allotUsers(userIds,tmIds);
        List<String> userIds2 = userRepository.findUserIdsByTerminalId("ab90f10e");
        System.out.println("terminal[ab90f10e] allot user size:" + userIds2.size());
    }

    @Test
    public void deleteUsersBitch() throws Exception {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("017440ad");
        userIdList.add("fd671478");
        userIdList.add("1f1b97ca");
        userIdList.add("4178f7e7");
        terminalService.deleteUsersBitch("ab90f10e",null,true);
    }

    @Test
    public void copyUserFromOtherTerminal() throws Exception {
        List<String> others = new ArrayList<>();
        others.add("a7719dc8");
        others.add("df6f10bf");
        others.add("24cba43e");
        terminalService.copyUserFromOtherTerminal("ab90f10e",others);

    }

    @Test
    public void addDutyLog() throws Exception {
        DutyDto dutyDto = new DutyDto();
        dutyDto.setUser_id("6af8597d");
        dutyDto.setMac_addr("00000003");
        dutyDto.setAttend_time("15:20");
        dutyDto.setCompare_score(99.99F);
        terminalService.addDutyLog(dutyDto);
    }

}