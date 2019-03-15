package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Auther: CHEN
 * @Date: 2019/3/14 15:15
 * @Description: 班次开放实体
 */
public class ClassesDto  extends BasePage implements Serializable {
    private static final long serialVersionUID = -6699496838813744370L;

    //班次ID
    private String att_cla_id;
    //班次名
    private String att_cla_name;

    private int att_cla_totalHours;
    // 是否弹性
    private int att_cla_isElastic;

    private ArrayList<ClassClockDto> claCloDtosList;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAtt_cla_id() {
        return att_cla_id;
    }

    public void setAtt_cla_id(String att_cla_id) {
        this.att_cla_id = att_cla_id;
    }

    public String getAtt_cla_name() {
        return att_cla_name;
    }

    public void setAtt_cla_name(String att_cla_name) {
        this.att_cla_name = att_cla_name;
    }

    public int getAtt_cla_totalHours() {
        return att_cla_totalHours;
    }

    public void setAtt_cla_totalHours(int att_cla_totalHours) {
        this.att_cla_totalHours = att_cla_totalHours;
    }

    public int getAtt_cla_isElastic() {
        return att_cla_isElastic;
    }

    public void setAtt_cla_isElastic(int att_cla_isElastic) {
        this.att_cla_isElastic = att_cla_isElastic;
    }


    public ArrayList<ClassClockDto> getClaCloDtosList() {
        return claCloDtosList;
    }

    public void setClaCloDtosList(ArrayList<ClassClockDto> claCloDtosList) {
        this.claCloDtosList = claCloDtosList;
    }
}
