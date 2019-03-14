package com.hjkj.cloud.attend.infrastructure.utils;

import java.util.UUID;

public class StringUtils {


    public static String genUUID(int num) {
        if (num <= 0 || num> 16) {
            num = 8;
        }
        return UUID.randomUUID().toString().replace("-","").substring(0,num);
    }



}
