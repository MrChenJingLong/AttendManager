package com.hjkj.cloud.attend.infrastructure.cache;

import java.util.Calendar;

public class UserState {

    //用户编码
    private String user_id;
    //用户名
    private String user_name;
    //当前时间
    private String now_date;
    //最后一次登录时间
    private String last_login_time;
    //登录状态
    private String online_status;
    //浏览器版本
    private String browser_version;
    //系统版本
    private String version;

    public UserState() {
    }

    public UserState(String userCode, String userName) {
        this.user_id = userCode;
        this.user_name = userName;
    }

    public UserState(String userCode, String userName, String browser_version, String version) {
        this.user_id = userCode;
        this.user_name = userName;
        Calendar cal=Calendar.getInstance();
        this.now_date = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE) + "日";
        this.last_login_time = this.now_date;
        this.online_status = "在线";
        this.browser_version = browser_version;
        this.version = version;
    }

    public void refresh() {
        Calendar cal=Calendar.getInstance();
        this.now_date = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE) + "日";
        this.last_login_time = this.now_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNow_date() {
        return now_date;
    }

    public void setNow_date(String now_date) {
        this.now_date = now_date;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getBrowser_version() {
        return browser_version;
    }

    public void setBrowser_version(String browser_version) {
        this.browser_version = browser_version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
