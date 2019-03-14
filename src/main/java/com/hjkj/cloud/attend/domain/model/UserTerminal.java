package com.hjkj.cloud.attend.domain.model;

public class UserTerminal {

    private String userId;
    private String terminalId;

    public UserTerminal() {
    }

    public UserTerminal(String userId, String terminalId) {
        this.userId = userId;
        this.terminalId = terminalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
