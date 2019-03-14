package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;
import java.util.List;

public class FullDepart implements Serializable {

    private static final long serialVersionUID = 4317524075165587272L;

    private String depart_id;
    private String depart_name;
    private String tag;
    private int level;
    private int is_private;
    private int sort;
    private int flag;
    private List<FullDepart> children;

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs_private() {
        return is_private;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<FullDepart> getChildren() {
        return children;
    }

    public void setChildren(List<FullDepart> children) {
        this.children = children;
    }
}
