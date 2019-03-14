package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;

/**
 * 混合排班表
 */
@Entity
@AllArgsConstructor
public class BlendSchedule extends AbstractEntity {


    @OneToOne(cascade=CascadeType.ALL)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_schedule_id")
    private Schedule childSchedule;
    // 月份
    private int month;

    public Schedule getSchedule() {
        return schedule;
    }

    @JsonBackReference
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getChildSchedule() {
        return childSchedule;
    }

    @JsonBackReference
    public void setChildSchedule(Schedule childSchedule) {
        this.childSchedule = childSchedule;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
