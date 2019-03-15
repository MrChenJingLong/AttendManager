package com.hjkj.cloud.attend.ui.dto.terminal;

import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;

public class DutyDto implements Serializable{
    private static final long serialVersionUID = -42121732826477234L;

    private String duty_id;
    @JLApiParam(name = "user_id",value = "用户ID",desc = "用户ID")
    private String user_id;
    private String user_name;
    @JLApiParam(name = "mac_addr",value = "终端mac地址",desc = "终端mac地址")
    private String mac_addr;
    private String tm_id;
    private String tm_name;
    @JLApiParam(name = "attend_type",value = "考勤类型",desc = "考勤类型")
    private String attend_type;
    private String attend_date;
    @JLApiParam(name = "attend_time",value = "考勤时间",desc = "考勤时间")
    private String attend_time;
    @JLApiParam(name = "attend_img",value = "现场照片",desc = "现场照片")
    private String attend_img;
    @JLApiParam(name = "compare_score",desc = "比对分数",type = PropType.INTEGER)
    private float compare_score;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMac_addr() {
        return mac_addr;
    }

    public void setMac_addr(String mac_addr) {
        this.mac_addr = mac_addr;
    }

    public String getAttend_type() {
        return attend_type;
    }

    public void setAttend_type(String attend_type) {
        this.attend_type = attend_type;
    }

    public String getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(String attend_time) {
        this.attend_time = attend_time;
    }

    public String getAttend_img() {
        return attend_img;
    }

    public void setAttend_img(String attend_img) {
        this.attend_img = attend_img;
    }

    public float getCompare_score() {
        return compare_score;
    }

    public void setCompare_score(float compare_score) {
        this.compare_score = compare_score;
    }

    public String getDuty_id() {
        return duty_id;
    }

    public void setDuty_id(String duty_id) {
        this.duty_id = duty_id;
    }

    public String getAttend_date() {
        return attend_date;
    }

    public void setAttend_date(String attend_date) {
        this.attend_date = attend_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTm_name() {
        return tm_name;
    }

    public void setTm_name(String tm_name) {
        this.tm_name = tm_name;
    }

    public String getTm_id() {
        return tm_id;
    }

    public void setTm_id(String tm_id) {
        this.tm_id = tm_id;
    }
}
