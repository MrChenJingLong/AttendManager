package com.hjkj.cloud.attend.ui.dto.web;

import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;

public class DepartDto extends BasePage implements Serializable {
    private static final long serialVersionUID = -3253353718196871842L;

    private String depart_id;
    private String parent_id;
    @JLApiParam(name = "depart_name",value = "部门名",desc = "部门名")
    private String depart_name;
    @JLApiParam(name = "tag",value = "tag",desc = "tag")
    private String tag;
    @JLApiParam(name = "level",desc = "level",type = PropType.INTEGER)
    private int level;
    @JLApiParam(name = "is_private",desc = "is_private",type = PropType.INTEGER)
    private int is_private;
    @JLApiParam(name = "sort",desc = "sort",type = PropType.INTEGER)
    private int sort;
    @JLApiParam(name = "flag",desc = "flag",type = PropType.INTEGER)
    private int flag;

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
