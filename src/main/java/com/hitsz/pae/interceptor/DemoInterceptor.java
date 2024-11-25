package com.hitsz.pae.interceptor;/*
 *@Author:Simon
 *@Date: 2024-11-10 - 2024 11 10 22:21
 *@Description:web-project-actual-combat
 *@version:1.0
 */

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component//交给ioc容器管理
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {

    /*controller方法运行之前，若为true表示放行*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DemoInterceptor preHandle");
        return true;
    }
    /*controller方法运行之后*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("DemoInterceptor postHandle");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /*最终运行的方法*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("DemoInterceptor afterCompletion");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
