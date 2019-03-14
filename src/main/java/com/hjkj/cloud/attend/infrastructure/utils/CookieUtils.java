package com.hjkj.cloud.attend.infrastructure.utils;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.infrastructure.cache.UserState;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    /**
     * 添加cookie信息
     */
    public static void addCookie(HttpServletResponse response,String token) {
        Cookie cookie;
        try {
            cookie = new Cookie(Constant.COOKIE_NAME,token);
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");
            cookie.setDomain("192.168.10.47");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserState getUserState(String token) throws Exception {
        token = URLDecoder.decode(token,"utf-8");
        token = SafeModel.Decrypt(token);
        return JSON.parseObject(token, UserState.class);
    }

    /**
     * 清空cookie信息
     */
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie;
        if(cookies!=null){
            for(Cookie c : cookies){
                cookie = new Cookie(c.getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        cookie = new Cookie("hj.token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
