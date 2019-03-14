package com.hjkj.cloud.attend.domain.model;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
public class Holiday extends AbstractEntity {

    // 名称
    @Column(length = 20)
    private String name;
    // 日期
    private Date date;
    // 补班日期
    private Date supDate;
    // 是否为工作日
    private int isWorkDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getSupDate() {
        return supDate;
    }

    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }

    public int getIsWorkDate() {
        return isWorkDate;
    }

    public void setIsWorkDate(int isWorkDate) {
        this.isWorkDate = isWorkDate;
    }
}
