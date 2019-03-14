package com.hjkj.cloud.attend.ui.dto.web;

import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;

public class RoleDto extends BasePage implements Serializable {
    private static final long serialVersionUID = 4540636918215982940L;

    @JLApiParam(name = "role_id",value = "角色Id",desc = "角色Id")
    private String role_id;
    @JLApiParam(name = "role_name",value = "角色名",desc = "角色名")
    private String role_name;
    @JLApiParam(name = "role_value",desc = "角色值",type = PropType.INTEGER)
    private int role_value;
    @JLApiParam(name = "role_sort",desc = "role_sort",type = PropType.INTEGER)
    private int role_sort;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRole_value() {
        return role_value;
    }

    public void setRole_value(int role_value) {
        this.role_value = role_value;
    }

    public int getRole_sort() {
        return role_sort;
    }

    public void setRole_sort(int role_sort) {
        this.role_sort = role_sort;
    }
}
