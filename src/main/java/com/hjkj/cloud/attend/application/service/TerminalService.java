package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TerminalService {

    /**
     * 分页查询终端信息
     */
    Page<TerminalDto> queryTerminals(TerminalDto terminalDto);

    /**
     * 查询所有终端信息
     */
    List<TerminalDto> findAllTerminal();

    /**
     * 终端信息注册
     */
    void registry(TerminalDto terminalInfo);

    /**
     * 批量删除终端信息
     */
    void deleteTerminals(List<String> tmIds);

    /**
     * 分页查询终端用户
     */
    Page<UserDto> queryUsersOfTerminal(String tmId, int page, int size);

    /**
     * 终端批量分配人员
     */
    void allotUsers(List<String> userIds,List<String> tmIds);

    /**
     * 批量删除终端用户
     */
    void deleteUsersBitch(String tmId,List<String> userIds,boolean isClear);

    /**
     * 清空终端用户
     */
    void clearUsersOnlyTerminal(String tmId);

    /**
     * 获取终端数据文件
     */
    void getTerminalDataByType(List<String> tmIds,String type);

    /**
     * 获取终端人员数据
     */
    void getUserData(String tmId);

    /**
     * 获取终端考勤数据
     */
    void getVerifyData(String tmId);

    /**
     * 从其他终端拷贝人员数据
     */
    void copyUserFromOtherTerminal(String sourceTmId,List<String> otherTmIds);

    /**
     * 下发人员数据到终端
     */
    void dispatchUsers(String tmId,List<String> userIds);

    /**
     * 终端添加注册人员
     */
    void addUserToTerminal(UserDto userDto);

    /**
     * 批量向终端添加注册人员
     */
    void addUsersToTerminal(List<UserDto> userDtoList);

    /**
     * 校验人员工号是否已注册
     */
    boolean isExistUser(String cardNum,String macAddr);

    /**
     * 删除用户
     */
    void delUserOfTerminal(String userId,String mac,boolean isDelRel,boolean isDelData);

    /**
     * 保存考勤记录
     */
    void addDutyLog(DutyDto dutyDto);


}
