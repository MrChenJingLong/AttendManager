package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class DutyManagerTest {

    @Autowired
    private DutyManager dutyManager;

    @Test
    public void saveDutyLog() throws Exception {
        Duty duty = new Duty();
        duty.setId(StringUtils.genUUID(8));
        duty.setDate(new Date());
        duty.setTime("9:18:20");
        duty.setCompareScore(99.9F);
        dutyManager.saveDutyLog(duty,"4ea40e43","00000001");
    }

}