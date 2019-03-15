package com.hjkj.cloud.attend.ui.dto.web;

import java.util.Date;

/**
 * @Auther: CHEN
 * @Date: 2019/3/15 16:28
 * @Description:
 */
public class ClassClockDto {


    private String id;

    private String clo_id;

    private String cla_id;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getCla_id() {
        return cla_id;
    }

    public void setCla_id(String cla_id) {
        this.cla_id = cla_id;
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
