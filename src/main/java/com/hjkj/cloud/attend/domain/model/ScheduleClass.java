package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 排班班次表
 */
@Entity
@AllArgsConstructor
public class ScheduleClass extends AbstractEntity {

    // 班次表
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    // 排班表
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // 班次序号
    private int classNo;

    public Classes getClasses() {
        return classes;
    }

    @JsonBackReference
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @JsonBackReference
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

}
