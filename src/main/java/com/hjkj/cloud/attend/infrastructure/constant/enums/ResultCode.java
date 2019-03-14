package com.hjkj.cloud.attend.infrastructure.constant.enums;

public enum ResultCode {

    SUCCESS(5000,"调用成功"),
    FAILED(5001,"服务内部错误"),
    NOT_FOUND(5004,"接口不存在");


    public int code;
    public String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
