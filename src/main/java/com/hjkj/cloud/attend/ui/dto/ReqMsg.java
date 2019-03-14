package com.hjkj.cloud.attend.ui.dto;

import com.alibaba.fastjson.JSON;
import hjkj.springframework.boot.doc.annotation.JLApiParam;

import java.io.Serializable;

public class ReqMsg implements Serializable {

    private static final long serialVersionUID = -2905784273868024403L;
    @JLApiParam(name = "request_source",value = "应用ID",desc = "应用ID")
    private String request_source;
    @JLApiParam(name = "verify_code",value = "校验码",desc = "校验码")
    private String verify_code;
    @JLApiParam(name = "request_time",value = "请求时间",desc = "请求时间")
    private String request_time;
    private String token;
    @JLApiParam(name = "content",value = "请求内容",desc = "请求内容")
    private String content;

    public void setRequest_source(String request_source) {
        this.request_source = request_source;
    }

    public String getRequest_source() {
        return request_source;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public <T> T getContentObject(Class<T> clazz) {
        return JSON.parseObject(content, clazz);
    }

    public static ReqMsg parseJSONString(String JSONString) {
        return JSON.parseObject(JSONString, ReqMsg.class);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
