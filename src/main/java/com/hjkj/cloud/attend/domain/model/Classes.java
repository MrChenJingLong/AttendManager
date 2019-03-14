package com.hjkj.cloud.attend.domain.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 班次表
 */
@Entity
@AllArgsConstructor
public class Classes extends AbstractEntity {

    // 名称
    @Column(length = 50)
    private String name;
    // 总工时
    private int totalHours;
    // 是否弹性
    private int isElastic;

    public Classes() {
    }

    @OneToMany(mappedBy = "classes",cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ClassClock> classClocks = new HashSet<>();

    @OneToMany(mappedBy = "classes",cascade= CascadeType.ALL,orphanRemoval = true)
    private Set<ScheduleClass> scheduleClasses = new HashSet<>();

    @OneToMany(mappedBy = "classes",cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserClasses> userClasses = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getIsElastic() {
        return isElastic;
    }

    public void setIsElastic(int isElastic) {
        this.isElastic = isElastic;
    }

    public Set<ClassClock> getClassClocks() {
        return classClocks;
    }

    public void setClassClocks(Set<ClassClock> classClocks) {
        this.classClocks = classClocks;
    }

    public Set<ScheduleClass> getScheduleClasses() {
        return scheduleClasses;
    }

    public void setScheduleClasses(Set<ScheduleClass> scheduleClasses) {
        this.scheduleClasses = scheduleClasses;
    }

    public Set<UserClasses> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(Set<UserClasses> userClasses) {
        this.userClasses = userClasses;
    }
}
