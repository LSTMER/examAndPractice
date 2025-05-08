package com.hitsz.pae.operationlog.config;/*
 *@Author:Simon
 *@Date: 2024-12-16 - 2024 12 16 22:52
 *@Description:xiaohashu
 *@version:1.0
 */


import com.hitsz.pae.operationlog.aspect.ApiOperationLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiOperationLogAutoConfiguration {

    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }
}
