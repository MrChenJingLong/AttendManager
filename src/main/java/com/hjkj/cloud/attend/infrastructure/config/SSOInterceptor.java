package com.hjkj.cloud.attend.infrastructure.config;


import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.cache.UserSession;
import com.hjkj.cloud.attend.infrastructure.cache.UserState;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.utils.SafeModel;
import com.hjkj.cloud.attend.infrastructure.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Component
public class SSOInterceptor implements HandlerInterceptor {

    private IUserRepository userRepository;

    @Autowired
    public SSOInterceptor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();

        if (null != cookies) { //cookie 不为空 则进行下一步操作
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_NAME.equals(cookie.getName())) {
                    try {
                        //解析cookie
                        String token = cookie.getValue();
                        token = URLDecoder.decode(token,"utf-8");
                        token = SafeModel.Decrypt(token);
                        UserState userState = JSON.parseObject(token, UserState.class);
                        userState.refresh();
                        UserSession.add(request.getSession().getId(),userState);

                        //检查当前用户是否有操作该接口的权限
                        return isGrantOfUser(userState.getUser_id(),request.getRequestURI());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //response.sendRedirect("");
        return false;
    }

    private boolean isGrantOfUser(String userId,String uri) {
        int res = userRepository.countByUserIdAndUrl(userId, uri);
        return res > 0;
    }

}
