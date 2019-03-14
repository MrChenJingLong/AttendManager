package com.hjkj.cloud.attend.jni.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class JsonTest {

    public static void main(String[] args) {
        String str = "{\"result_code\":\"0\",\"content\":[{\"key_id\":\"1054\",\"compare_score\":\"23.6253\"}{\"key_id\":\"1005\",\"compare_score\":\"22.4652\"}{\"key_id\":\"1069\",\"compare_score\":\"18.2034\"}{\"key_id\":\"1059\",\"compare_score\":\"14.8722\"}{\"key_id\":\"1145\",\"compare_score\":\"14.482\"}{\"key_id\":\"1050\",\"compare_score\":\"13.3074\"}{\"key_id\":\"1060\",\"compare_score\":\"12.5665\"}{\"key_id\":\"1033\",\"compare_score\":\"11.5834\"}{\"key_id\":\"1067\",\"compare_score\":\"11.2104\"}{\"key_id\":\"1037\",\"compare_score\":\"10.8388\"}]}";
        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray content = jsonObject.getJSONArray("content");
        Iterator<Object> it = content.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            System.out.println(next);
        }
    }

}
