package com.hjkj.cloud.attend.infrastructure.exception;

import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8558221873595548019L;

    private int code;

    public ServiceException() {
    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.desc);
        this.code = resultCode.code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
