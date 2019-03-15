package com.hjkj.cloud.attend.domain.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 考勤时段表
 */
@Entity
@AllArgsConstructor
public class Clock extends AbstractEntity {

    private String id;

    // 时段名称
    @Column(length = 50)
    private String name;
    // 时段序号
    private int sort;


    @OneToMany(mappedBy = "clock",cascade= CascadeType.ALL,orphanRemoval = true)
    private Set<ClassClock> classClocks = new HashSet<>();


    public Clock() {
    }

    public Clock(String id) {
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Set<ClassClock> getClassClocks() {
        return classClocks;
    }

    public void setClassClocks(Set<ClassClock> classClocks) {
        this.classClocks = classClocks;
    }
}
