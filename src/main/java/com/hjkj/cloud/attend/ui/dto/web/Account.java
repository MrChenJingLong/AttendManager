package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 3803028511197188342L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
