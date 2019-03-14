package com.hjkj.cloud.attend.ui.dto.web;

import java.io.Serializable;
import java.util.Date;

public class QueryDuty extends BasePage implements Serializable {
    private static final long serialVersionUID = 5604456782814476740L;

    private String user_card_num;
    private String terminal_name;
    private Date start_time;
    private Date end_time;

    public String getUser_card_num() {
        return user_card_num;
    }

    public void setUser_card_num(String user_card_num) {
        this.user_card_num = user_card_num;
    }

    public String getTerminal_name() {
        return terminal_name;
    }

    public void setTerminal_name(String terminal_name) {
        this.terminal_name = terminal_name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
