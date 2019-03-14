package com.hjkj.cloud.attend.infrastructure.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjkj.cloud.attend.application.service.AuthService;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Autowired
    private AuthService authService;

    /**
     * 白名单
     */
   private static final String[] IgnoreUrls = new String[] {
            "/attend/sys/.*",
            "/attend/tm/.*",
            "/web-api",
            "/health",
            "/info"
   };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1、忽略验证的URL
        String url = request.getRequestURI();
        for(String ignoreUrl : IgnoreUrls){
            if(url.matches(ignoreUrl)){
                return true;
            }
        }

        // 2、查询验证注解 PreFlightHandler
        if (!(handler instanceof HandlerMethod)) {
            logger.info("handler instanceof " + handler.getClass());
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 查询注解
        Auth auth = method.getAnnotation(Auth.class);
        if (auth == null) {
            // 无注解，不需要
            return true;
        }

        // 3、有注解，先检查token
        String token = (String) getParameter("token", request);
        logger.info("token:" + token);
        if(StringUtils.isEmpty(token)){
            output(RetMsgGenerator.genFailedRetMsg("access token is null"),response);
            return false;
        }
        // 检验token是否过期
        String userId = authService.getUserIdFromToken(token);
        if(userId == null){
            logger.debug("access token timeout");
            output(RetMsgGenerator.genFailedRetMsg("access token timeout"),response);
            return false;
        }

        //更新token的过期时间
        authService.refreshTokenTime(token);

        // 4、再检验是否包含必要的岗位
        if(auth.posts().length > 0){
            User user = authService.getUser(userId);
            Set<String> authSet = new HashSet<>();
            for (Post post : user.getPosts()) {
                authSet.add(post.getName());
            }
            boolean isMatch = false;
            for (String postName : auth.posts()) {
                if (authSet.contains(postName)) {
                    isMatch = true;
                }
            }
            if (!isMatch) {
                output(RetMsgGenerator.genFailedRetMsg("invalid access permission"),response);
                return false;
            }
        }

        return true;
    }

    private Object getParameter(String param,HttpServletRequest request) {
        if ("GET".equals(request.getMethod())) {
            return request.getParameter(param);
        } else if ("POST".equals(request.getMethod())) {
            return getParamFromBody(param,request);
        }
        throw new ServiceException(ResultCode.FAILED);
    }

    private Object getParamFromBody(String param,HttpServletRequest request) {
        JSONObject parameterMap = JSON.parseObject(new BodyReaderRequestWrapper(request).getBodyString(request));
        return parameterMap == null ? null : parameterMap.get(param);
    }


    private void output(RetMsg result,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

}
