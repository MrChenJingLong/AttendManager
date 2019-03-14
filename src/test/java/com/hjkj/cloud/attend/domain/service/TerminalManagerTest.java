package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.repository.ITerminalRepository;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerminalManagerTest {

    @Autowired
    private TerminalManager terminalManager;
    @Autowired
    private ITerminalRepository terminalRepository;

    @Test
    @Transactional
    public void saveTerminalInfo() throws Exception {
        for (int i = 1;i <= 10;i++) {
            Terminal terminal = new Terminal();
            terminal.setId(StringUtils.genUUID(8));
            terminal.setMacAddr("0000000" + i);
            terminal.setTmAllowNum(1000);
            terminal.setTmCompRecords(0);
            terminal.setTmIp("127.0.0.1");
            terminal.setTmPort(8721);
            terminal.setTmRegnum(0);
            terminal.setTmRegTime(new Date());
            terminal.setTmStrangerRecords(0);
            terminal.setTmTotalCap(100);
            terminal.setTmUsableCap(0);
            terminal.setVersion("V1.1");
            terminal.setAcceptState(1);
            terminal.setTransmitState(1);
            terminalManager.saveTerminalInfo(terminal,"fdd966ec");
        }

    }

    @Test
    @Transactional
    public void updateAllTerminalRegisterNum() throws Exception {
        List<Terminal> terminalList = terminalRepository.findAll();
        for (Terminal terminal : terminalList) {
            terminal.setTmRegnum(0);
            terminalRepository.saveAndFlush(terminal);
        }
    }

    @Test
    public void refreshRegisterNumber() {
        terminalRepository.refreshRegisterNumber("a7719dc8");
    }

    @Test
    public void deleteTerminalById() throws Exception {
        terminalRepository.deleteUsersByTerminalId("af418e03");
    }

}