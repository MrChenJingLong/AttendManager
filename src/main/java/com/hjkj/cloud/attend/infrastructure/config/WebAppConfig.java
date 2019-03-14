package com.hjkj.cloud.attend.infrastructure.config;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

    @Autowired
    private AuthCheckInterceptor authCheckInterceptor;

//    //访问默认首页
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/Home");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }

//    //注册拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCheckInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((request, response, handler, ex) -> {
            RetMsg.Builder builder = new RetMsg.Builder();
            if (ex instanceof ServiceException) {
                ServiceException serviceException = (ServiceException) ex;
                builder.setResult_code(serviceException.getCode()).setResult_desc(serviceException.getMessage());
            } else if (ex instanceof NoHandlerFoundException) {
                builder.setResult_code(ResultCode.NOT_FOUND.code).setResult_desc("接口 [" + request.getRequestURI() + "] 不存在");
            } else if (ex instanceof ServletException) {
                builder.setResult_code(ResultCode.FAILED.code).setResult_desc(ex.getMessage());
            } else {
                builder.setResult_code(ResultCode.FAILED.code).setResult_desc("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            ex.getMessage());
                } else {
                    message = ex.getMessage();
                }
                logger.error(message, ex);
            }
            responseResult(response, builder.build());
            return new ModelAndView();
        });
    }

    private void responseResult(HttpServletResponse response, RetMsg result) {
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
