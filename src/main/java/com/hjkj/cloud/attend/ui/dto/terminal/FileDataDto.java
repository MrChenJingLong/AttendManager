package com.hjkj.cloud.attend.ui.dto.terminal;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class FileDataDto implements Serializable {

    private static final long serialVersionUID = 2119217638939020938L;

    private String type;
    private MultipartFile data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile getData() {
        return data;
    }

    public void setData(MultipartFile data) {
        this.data = data;
    }
}
