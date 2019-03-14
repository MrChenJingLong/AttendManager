package com.hjkj.cloud.attend.domain.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 排班表
 */
@Entity
@AllArgsConstructor
public class Schedule extends AbstractEntity {

    // 排班名称
    @Column(length = 20)
    private String name;
    // 开始日期
    private Date startDate;
    // 周期天数
    private int loopTime;
    // 节假日是否自动排休
    private int isAutoSchedule;
    // 是否为混合排班
    private int isBlendSchedule;

    @OneToMany(mappedBy = "schedule",cascade= CascadeType.ALL)
    private Set<ScheduleClass> scheduleClasses = new HashSet<>();

    @OneToOne(mappedBy = "schedule", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private BlendSchedule blendSchedule;

    @OneToMany(mappedBy = "childSchedule",cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlendSchedule> blendSchedules = new HashSet<>();

    @OneToMany(mappedBy = "schedule",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(int loopTime) {
        this.loopTime = loopTime;
    }

    public int getIsAutoSchedule() {
        return isAutoSchedule;
    }

    public void setIsAutoSchedule(int isAutoSchedule) {
        this.isAutoSchedule = isAutoSchedule;
    }

    public int getIsBlendSchedule() {
        return isBlendSchedule;
    }

    public void setIsBlendSchedule(int isBlendSchedule) {
        this.isBlendSchedule = isBlendSchedule;
    }

    public Set<ScheduleClass> getScheduleClasses() {
        return scheduleClasses;
    }

    public void setScheduleClasses(Set<ScheduleClass> scheduleClasses) {
        this.scheduleClasses = scheduleClasses;
    }

    public BlendSchedule getBlendSchedule() {
        return blendSchedule;
    }

    public void setBlendSchedule(BlendSchedule blendSchedule) {
        this.blendSchedule = blendSchedule;
    }

    public Set<BlendSchedule> getBlendSchedules() {
        return blendSchedules;
    }

    public void setBlendSchedules(Set<BlendSchedule> blendSchedules) {
        this.blendSchedules = blendSchedules;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
