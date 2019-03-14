package com.hjkj.cloud.attend.domain.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
public class MonthDuty extends AbstractEntity {

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 总的天数
    private int totalDay;
    // 实到天数
    private int actualDay;
    // 迟到天数
    private int lateDay;
    // 早退天数
    private int earlyDay;
    // 缺勤天数
    private int absenceDay;
    // 记录月份
    private Date date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }

    public int getActualDay() {
        return actualDay;
    }

    public void setActualDay(int actualDay) {
        this.actualDay = actualDay;
    }

    public int getLateDay() {
        return lateDay;
    }

    public void setLateDay(int lateDay) {
        this.lateDay = lateDay;
    }

    public int getEarlyDay() {
        return earlyDay;
    }

    public void setEarlyDay(int earlyDay) {
        this.earlyDay = earlyDay;
    }

    public int getAbsenceDay() {
        return absenceDay;
    }

    public void setAbsenceDay(int absenceDay) {
        this.absenceDay = absenceDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
