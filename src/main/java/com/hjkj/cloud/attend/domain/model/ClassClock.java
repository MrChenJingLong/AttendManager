package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 *  班次时段表
 */
@Entity
@AllArgsConstructor
public class ClassClock extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clock_id")
    private Clock clock;


    public ClassClock() {
    }

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

    public Classes getClasses() {
        return classes;
    }

    @JsonBackReference
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Clock getClock() {
        return clock;
    }

    @JsonBackReference
    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
