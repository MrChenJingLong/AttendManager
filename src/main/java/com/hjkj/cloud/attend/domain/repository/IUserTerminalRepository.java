package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.UserTerminal;

import java.util.List;

/**
 * 用户终端关联
 */
public interface IUserTerminalRepository {

    /**
     * 批量插入数据
     */
    void insertUserTerminalBatch(List<UserTerminal> userTerminals);

}
