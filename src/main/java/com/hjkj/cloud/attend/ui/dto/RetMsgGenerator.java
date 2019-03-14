package com.hjkj.cloud.attend.ui.dto;

import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;

public class RetMsgGenerator {

    public static RetMsg genSuccessRetMsg() {
        return new RetMsg.Builder(ResultCode.SUCCESS.code,ResultCode.SUCCESS.desc).build();
    }

    public static RetMsg genSuccessRetMsg(String msg) {
        return new RetMsg.Builder(ResultCode.SUCCESS.code,msg).build();
    }

    public static RetMsg genSuccessRetMsg(String msg,Object obj) {
        return new RetMsg.Builder(ResultCode.SUCCESS.code,msg).setContent(obj).build();
    }

    public static RetMsg genFailedRetMsg() {
        return new RetMsg.Builder(ResultCode.FAILED.code,ResultCode.SUCCESS.desc).build();
    }

    public static RetMsg genFailedRetMsg(String msg) {
        return new RetMsg.Builder(ResultCode.FAILED.code,msg).build();
    }


}
