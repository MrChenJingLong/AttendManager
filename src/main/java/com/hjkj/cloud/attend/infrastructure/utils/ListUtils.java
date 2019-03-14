package com.hjkj.cloud.attend.infrastructure.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListUtils {

    /**
     * 通过HashSet踢除重复元素
     */
    public static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<>(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
