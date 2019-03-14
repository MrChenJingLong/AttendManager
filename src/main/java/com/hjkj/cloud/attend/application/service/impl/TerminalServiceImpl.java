package com.hjkj.cloud.attend.application.service.impl;

import com.hjkj.cloud.attend.application.convert.DomainAssemble;
import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.repository.ITerminalRepository;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.domain.service.DepartmentManager;
import com.hjkj.cloud.attend.domain.service.DutyManager;
import com.hjkj.cloud.attend.domain.service.UserManager;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.application.service.TerminalService;
import com.hjkj.cloud.attend.domain.service.TerminalManager;
import com.hjkj.cloud.attend.application.convert.DtoTransform;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.infrastructure.utils.ListUtils;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 终端管理服务
 */
@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private TerminalManager terminalManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private DutyManager dutyManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITerminalRepository terminalRepository;

    @Override
    public Page<TerminalDto> queryTerminals(TerminalDto terminalDto) {
        Terminal terminal = DtoTransform.copyOfTerminalDto(terminalDto);
        List<String> departIds = null;
        if (terminal.getDepartment() != null) {
            departIds = departmentManager.containsDepartIds(terminal.getDepartment().getId());
        }
        Page<Terminal> terminalPage = terminalManager.findTerminalCriteria(terminalDto.getPage(), terminalDto.getSize(), terminal, departIds);
        return DomainAssemble.copyOfTerminalPage(terminalPage);
    }

    @Override
    public List<TerminalDto> findAllTerminal() {
        List<Terminal> terminalList = terminalRepository.findAll();
        return DomainAssemble.copyOfTerminalList(terminalList);
    }

    @Override
    public void registry(TerminalDto terminalInfo) {
        terminalManager.saveTerminalInfo(DtoTransform.copyOfTerminalDto(terminalInfo),terminalInfo.getDepart_id());
    }

    @Override
    public void deleteTerminals(List<String> tmIds) {
        if (tmIds == null) {
            throw new ServiceException(ResultCode.FAILED.code,"请选择要删除的终端");
        }
        terminalManager.deleteTerminalBatch(tmIds);
    }

    @Override
    public Page<UserDto> queryUsersOfTerminal(String tmId, int page, int size) {
        Terminal terminal = terminalManager.findTerminalById(tmId);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "cardNum");
        Page<User> userPage = userRepository.findUsersByTerminalsContainsOrderByRegTime(terminal, pageable);
        return DomainAssemble.copyOfUserPage(userPage);
    }

    @Override
    public void allotUsers(List<String> userIds, List<String> tmIds) {
        userManager.allotUsers(tmIds,userIds);
    }

    @Override
    @Transactional
    public void deleteUsersBitch(String tmId, List<String> userIds,boolean isClear) {
        // 删除终端的用户关联数据
        if ((userIds == null || userIds.size() <= 0) && isClear) {
            terminalRepository.deleteUsersByTerminalId(tmId);
        } else {
            terminalRepository.deleteUsersByTerminalIdAndUserIds(tmId,userIds);
        }
        // 刷新终端注册人数
        terminalRepository.refreshRegisterNumber(tmId);
    }

    @Override
    public void clearUsersOnlyTerminal(String tmId) {
        terminalRepository.deleteUsersByTerminalId(tmId);
    }

    @Override
    public void getTerminalDataByType(List<String> tmIds, String type) {
        // TODO: 获取终端数据文件
    }

    @Override
    public void getUserData(String tmId) {
        // TODO: 获取终端人员数据文件
    }

    @Override
    public void getVerifyData(String tmId) {
        // TODO: 获取终端考勤数据文件
    }

    @Override
    public void copyUserFromOtherTerminal(String sourceTmId, List<String> otherTmIds) {
        //查询注册在终端集合otherTmIds的用户id
        List<String> userIdList = userRepository.findUserIdsByTerminalIds(otherTmIds);
        // 给终端分配人员
        userManager.allotUsers(sourceTmId,userIdList);
    }



    @Override
    public void dispatchUsers(String tmId, List<String> userIds) {
        // TODO: 下发人员数据到终端
    }

    @Override
    public boolean isExistUser(String cardNum,String macAddr) {
        return userManager.isExistsUser(cardNum,macAddr);
    }

    @Override
    public void addUserToTerminal(UserDto userDto) {
        User user = DtoTransform.copyOfUserDto(userDto);
        userManager.register(user,userDto.getMac_addr());
    }

    @Override
    public void addUsersToTerminal(List<UserDto> userDtoList) {
        for (UserDto userDto : userDtoList) {
            User user = DtoTransform.copyOfUserDto(userDto);
            userManager.register(user,userDto.getMac_addr());
        }
    }

    @Override
    public void delUserOfTerminal(String userId, String mac,boolean isDelRel,boolean isDelData) {
        userManager.delUserByMacAddr(userId,mac,isDelRel,isDelData);
    }

    @Override
    public void addDutyLog(DutyDto dutyDto) {
        dutyManager.saveDutyLog(DtoTransform.copyOfDutyDto(dutyDto),dutyDto.getUser_id(),dutyDto.getMac_addr());
    }
}
