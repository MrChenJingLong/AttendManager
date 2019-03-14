package com.hjkj.cloud.attend.ui.dto.terminal;

import com.hjkj.cloud.attend.infrastructure.constant.enums.FileType;

import java.io.Serializable;

public class AttendRecordDto implements Serializable {
    private static final long serialVersionUID = 967438473893291116L;

    private String user_id;
    private FileType attend_type;
    private String attend_time;
    private String attend_img;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public FileType getAttend_type() {
        return attend_type;
    }

    public void setAttend_type(FileType attend_type) {
        this.attend_type = attend_type;
    }

    public String getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(String attend_time) {
        this.attend_time = attend_time;
    }

    public String getAttend_img() {
        return attend_img;
    }

    public void setAttend_img(String attend_img) {
        this.attend_img = attend_img;
    }
}
