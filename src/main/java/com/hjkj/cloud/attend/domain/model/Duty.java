package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 考勤实体
 */
@Entity
@AllArgsConstructor
public class Duty extends AbstractEntity {

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;
    @Column(length = 50)
    private String time;
    private float compareScore;

    @Transient
    private Date startDate;
    @Transient
    private Date endDate;

    public Duty() {
    }

    public Terminal getTerminal() {
        return terminal;
    }

    @JsonBackReference
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public User getUser() {
        return user;
    }

    @JsonBackReference
    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getCompareScore() {
        return compareScore;
    }

    public void setCompareScore(float compareScore) {
        this.compareScore = compareScore;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
