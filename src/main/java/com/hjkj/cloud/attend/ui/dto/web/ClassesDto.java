package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;
import java.util.Date;

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

    private String att_clo_id;

    // 上班打卡时间
    private Date onWorkClockDate;
    // 上班时间
    private Date onWorkDate;
    // 上班是否需要打卡
    private int onWorkClockState;
    // 下班时间
    private Date offWorkDate;
    // 下班打卡时间
    private Date offWorkClockDate;
    // 下班是否需要打卡
    private int offWorkClockState;
    // 允许迟到时间
    private int allowLateTime;
    // 允许早退时间
    private int allowEarlyTime;






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

    public String getAtt_clo_id() {
        return att_clo_id;
    }

    public void setAtt_clo_id(String att_clo_id) {
        this.att_clo_id = att_clo_id;
    }

    public Date getOnWorkClockDate() {
        return onWorkClockDate;
    }

    public void setOnWorkClockDate(Date onWorkClockDate) {
        this.onWorkClockDate = onWorkClockDate;
    }

    public Date getOnWorkDate() {
        return onWorkDate;
    }

    public void setOnWorkDate(Date onWorkDate) {
        this.onWorkDate = onWorkDate;
    }

    public int getOnWorkClockState() {
        return onWorkClockState;
    }

    public void setOnWorkClockState(int onWorkClockState) {
        this.onWorkClockState = onWorkClockState;
    }

    public Date getOffWorkDate() {
        return offWorkDate;
    }

    public void setOffWorkDate(Date offWorkDate) {
        this.offWorkDate = offWorkDate;
    }

    public Date getOffWorkClockDate() {
        return offWorkClockDate;
    }

    public void setOffWorkClockDate(Date offWorkClockDate) {
        this.offWorkClockDate = offWorkClockDate;
    }

    public int getOffWorkClockState() {
        return offWorkClockState;
    }

    public void setOffWorkClockState(int offWorkClockState) {
        this.offWorkClockState = offWorkClockState;
    }

    public int getAllowLateTime() {
        return allowLateTime;
    }

    public void setAllowLateTime(int allowLateTime) {
        this.allowLateTime = allowLateTime;
    }

    public int getAllowEarlyTime() {
        return allowEarlyTime;
    }

    public void setAllowEarlyTime(int allowEarlyTime) {
        this.allowEarlyTime = allowEarlyTime;
    }
}
