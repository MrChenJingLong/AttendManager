package com.hjkj.cloud.attend.infrastructure.constant;

import java.text.SimpleDateFormat;

public class Constant {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    //转发或接受的状态
    public static final int STATE_ON = 1;

    public static final int STATE_OFF = 0;

    public static final String COMPANY = "福建海景科技有限公司";

    public static final String COPY_RIGHT = "@";

    public static final String COOKIE_NAME = "hj.token";

    public static final Long EXPIRE_TIME = 3600L;

    public static final String ATTEND_ALL_MENUS = "attend_all_menus";

    public static final String DEFAULT_PASSWORD = "123456";

}


