package com.hjkj.cloud.attend.infrastructure.constant.enums;

public enum FileType {
    USER_DATA("6000"),
    VERIFY_LOG("6001"),
    STRANGER_DATA("6002"),
    PARAM_DATA("6003"),
    OPERATE_LOG("6004"),
    SCREEN_SAVER_IMG("6005"),
    ALARM_RECORD("6006"),
    UPGRADE_DATA("6007"),
    USER_AUTH_DATA("6008"),
    VIDEO_STREAM("6009");

    public String code;

    FileType(String code) {
        this.code = code;
    }
}
