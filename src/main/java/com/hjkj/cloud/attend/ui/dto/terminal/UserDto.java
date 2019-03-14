package com.hjkj.cloud.attend.ui.dto.terminal;

import com.hjkj.cloud.attend.ui.dto.web.BasePage;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;
import java.util.Date;

public class UserDto extends BasePage implements Serializable {
    private static final long serialVersionUID = 6955142705471378122L;

    @JLApiParam(name = "mac_addr",value = "终端mac地址",desc = "终端mac地址")
    private String mac_addr;
    @JLApiParam(name = "user_id",value = "用户Id",desc = "用户Id")
    private String user_id;
    @JLApiParam(name = "user_name",value = "用户名",desc = "用户名")
    private String user_name;
    @JLApiParam(name = "user_sex",value = "性别",desc = "性别")
    private String user_sex;
    @JLApiParam(name = "reg_time",value = "用户注册时间",desc = "用户注册时间")
    private String reg_time;
    @JLApiParam(name = "card_num",value = "工号",desc = "工号")
    private String card_num;
    @JLApiParam(name = "password",value = "用户密码",desc = "用户密码")
    private String password;
    @JLApiParam(name = "ic_card",value = "IC卡卡号",desc = "IC卡卡号")
    private String ic_card;
    @JLApiParam(name = "role",value = "角色",desc = "0001：普通用户\n" +
            "0002：普通管理员\n" +
            "0003：超级管理员")
    private String role;
    @JLApiParam(name = "user_template",value = "base64人员模板",desc = "base64人员模板")
    private String user_template;
    @JLApiParam(name = "user_img",value = "base64人员图片",desc = "base64人员图片")
    private String user_img;
    //是否删除关联终端的用户
    private boolean is_del_rel = true;
    //是否删除用户数据
    private boolean is_del_data = false;
    //入职时间
    @JLApiParam(name = "hire_date",value = "入职时间",desc = "入职时间")
    private Date hire_date;
    //离职时间
    @JLApiParam(name = "leave_date",value = "离职时间",desc = "离职时间")
    private Date leave_date;
    //工作状态
    @JLApiParam(name = "work_state",type = PropType.INTEGER)
    private int work_state = -1;
    //出生日期
    @JLApiParam(name = "born_date",value = "出生日期",desc = "出生日期")
    private Date born_date;
    //代理人
    @JLApiParam(name = "agent",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    private UserDto agent;
    //是否为考勤人员
    @JLApiParam(name = "is_attendancer",value = "是否为考勤人员",desc = "是否为考勤人员")
    private int is_attendancer = -1;
    //所属部门ID
    @JLApiParam(name = "depart_id",value = "所属部门ID",desc = "所属部门ID")
    private String depart_id;
    ////所属部门名
    private String depart_name;
    //所属岗位ID
    @JLApiParam(name = "post_id",value = "所属岗位ID",desc = "所属岗位ID")
    private String post_id;

    //所属岗位名
    private String post_name;
    //所属角色ID
    @JLApiParam(name = "role_id",value = "所属角色ID",desc = "所属角色ID")
    private String role_id;
    //所属角色名
    private String role_name;
    //注册状态
    private int reg_state = -1;
    // 终端ID
    private String tm_id;

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

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIc_card() {
        return ic_card;
    }

    public void setIc_card(String ic_card) {
        this.ic_card = ic_card;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_template() {
        return user_template;
    }

    public void setUser_template(String user_template) {
        this.user_template = user_template;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getMac_addr() {
        return mac_addr;
    }

    public void setMac_addr(String mac_addr) {
        this.mac_addr = mac_addr;
    }

    public boolean isIs_del_rel() {
        return is_del_rel;
    }

    public void setIs_del_rel(boolean is_del_rel) {
        this.is_del_rel = is_del_rel;
    }

    public boolean isIs_del_data() {
        return is_del_data;
    }

    public void setIs_del_data(boolean is_del_data) {
        this.is_del_data = is_del_data;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(Date leave_date) {
        this.leave_date = leave_date;
    }

    public int getWork_state() {
        return work_state;
    }

    public void setWork_state(int work_state) {
        this.work_state = work_state;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public int getIs_attendancer() {
        return is_attendancer;
    }

    public void setIs_attendancer(int is_attendancer) {
        this.is_attendancer = is_attendancer;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public int getReg_state() {
        return reg_state;
    }

    public void setReg_state(int reg_state) {
        this.reg_state = reg_state;
    }

    public UserDto getAgent() {
        return agent;
    }

    public void setAgent(UserDto agent) {
        this.agent = agent;
    }

    public String getTm_id() {
        return tm_id;
    }

    public void setTm_id(String tm_id) {
        this.tm_id = tm_id;
    }
}
