package com.hjkj.cloud.attend.ui.dto.web;

import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;

public class PostDto extends BasePage implements Serializable {
    private static final long serialVersionUID = -6699496838813744370L;

    @JLApiParam(name = "role_id",value = "角色Id",desc = "角色Id")
    private String role_id;
    private String role_name;
    @JLApiParam(name = "depart_id",value = "部门Id",desc = "部门Id")
    private String depart_id;
    private String depart_name;
    private String post_id;
    @JLApiParam(name = "post_name",value = "岗位名",desc = "岗位名")
    private String post_name;
    @JLApiParam(name = "post_value",value = "岗位值",desc = "岗位值")
    private int post_value;
    @JLApiParam(name = "post_tag",value = "post_tag",desc = "post_tag")
    private String post_tag;
    @JLApiParam(name = "post_sort",desc = "post_sort",type = PropType.INTEGER)
    private int post_sort;
    @JLApiParam(name = "post_flag",desc = "post_flag",type = PropType.INTEGER)
    private int post_flag;

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public int getPost_value() {
        return post_value;
    }

    public void setPost_value(int post_value) {
        this.post_value = post_value;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getPost_tag() {
        return post_tag;
    }

    public void setPost_tag(String post_tag) {
        this.post_tag = post_tag;
    }

    public int getPost_sort() {
        return post_sort;
    }

    public void setPost_sort(int post_sort) {
        this.post_sort = post_sort;
    }

    public int getPost_flag() {
        return post_flag;
    }

    public void setPost_flag(int post_flag) {
        this.post_flag = post_flag;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }
}
