package com.hjkj.cloud.attend.ui.dto.web;

/**
 * @Auther: CHEN
 * @Date: 2019/3/15 15:16
 * @Description: 时段
 */
public class ClockDto {

    private String clo_id;

    private String clo_name;

    private int clo_sort;


    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getClo_name() {
        return clo_name;
    }

    public void setClo_name(String clo_name) {
        this.clo_name = clo_name;
    }

    public int getClo_sort() {
        return clo_sort;
    }

    public void setClo_sort(int clo_sort) {
        this.clo_sort = clo_sort;
    }
}
