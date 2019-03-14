package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.IDutyRepository;
import com.hjkj.cloud.attend.domain.repository.ITerminalRepository;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DutyManager {

    @Autowired
    private IDutyRepository dutyRepository;
    @Autowired
    private ITerminalRepository terminalRepository;
    @Autowired
    private IUserRepository userRepository;

    public void saveDutyLog(Duty duty,String userId,String macAddr) {
        if (StringUtils.isEmpty(duty.getId())) {
            duty.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        User user = findUserById(userId);
        duty.setUser(user);
        Terminal terminal = findTerminalByMacAddr(macAddr);
        duty.setTerminal(terminal);
        dutyRepository.saveAndFlush(duty);
    }

    private Terminal findTerminalByMacAddr(String macAddr) {
        Optional<Terminal> terminal = terminalRepository.findByMacAddr(macAddr);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + macAddr + "]不存在");
        }
        return terminal.get();
    }

    private User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到用户[" + userId + "]"));
    }

}
