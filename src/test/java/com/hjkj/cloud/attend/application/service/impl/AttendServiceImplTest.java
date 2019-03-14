package com.hjkj.cloud.attend.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.application.service.AttendService;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.web.QueryDuty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class AttendServiceImplTest {

    @Autowired
    private AttendService attendService;

    @Test
    public void queryDutyInfo() throws Exception {
        QueryDuty queryDuty = new QueryDuty();
        queryDuty.setUser_card_num("10001");
        queryDuty.setTerminal_name("设备1");
         queryDuty.setEnd_time(new Date());
        queryDuty.setPage(0);
        queryDuty.setSize(7);
        Page<DutyDto> dutyDtos = attendService.queryDutyInfo(queryDuty);
        System.out.println(JSON.toJSONString(dutyDtos));
    }

}