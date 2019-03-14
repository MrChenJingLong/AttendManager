package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;
import java.util.List;

public class GrantDto implements Serializable {

    private static final long serialVersionUID = 9213771945178797067L;

    private String menu_id;
    private String name;
    private String url;
    private String ref;
    private String icon;
    private int sort;
    private int user_limit;
    private int type;
    private boolean hidden;
    private List<GrantDto> children;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getUser_limit() {
        return user_limit;
    }

    public void setUser_limit(int user_limit) {
        this.user_limit = user_limit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<GrantDto> getChildren() {
        return children;
    }

    public void setChildren(List<GrantDto> children) {
        this.children = children;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
