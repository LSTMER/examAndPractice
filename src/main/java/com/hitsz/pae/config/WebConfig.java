package com.hitsz.pae.config;/*
 *@Author:Simon
 *@Date: 2024-11-10 - 2024 11 10 22:25
 *@Description:web-project-actual-combat
 *@version:1.0
 */


import com.hitsz.pae.interceptor.DemoInterceptor;
import com.hitsz.pae.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 配置类，配置拦截器
*
* */
@Configuration//包含了component，交给了ioc容器管理
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    DemoInterceptor demoInterceptor;
    @Autowired
    TokenInterceptor tokenInterceptor;
    /*添加拦截器，注册拦截器，可以根据需要，配置不同的拦截路径*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//不需要拦截什么路径
    }
}
