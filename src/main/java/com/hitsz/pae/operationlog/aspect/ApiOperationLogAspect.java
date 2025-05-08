package com.hitsz.pae.operationlog.aspect;/*
 *@Author:Simon
 *@Date: 2024-12-16 - 2024 12 16 20:09
 *@Description:xiaohashu
 *@version:1.0
 */


import com.hitsz.pae.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Slf4j
public class ApiOperationLogAspect {
    /*以自定义 @ApiOperationLog 注解为切点，凡是添加了@ApiOperationLog 的方法，都会执行环绕中的代码*/
    @Pointcut("@annotation(com.hitsz.pae.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    /**
     * 环绕
     * @parm joinPoint
     * @return
     * @throw Trowable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        // 请求开始时间
        long startTime = System.currentTimeMillis();

        // 获取被请求的类和方法
        String className = joinPoint.getTarget().getClass().toString();
        String methodName = joinPoint.getSignature().getName();

        // 请求入参
        Object[] args = joinPoint.getArgs();
        // 入参转 JSON 字符串
        String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

        // 功能描述信息
        String description = getApiOperationLogDescription(joinPoint);

        log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                description, argsJsonStr, className, methodName);

        // 执行切点方法
        Object result = joinPoint.proceed(args);

        // 执行耗时
        long executionTime = System.currentTimeMillis()  - startTime;

        // 打印出参等信息
        log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                description, executionTime, JsonUtils.toJsonString(result));

        return result;
    }

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint){
        // 1. 从 ProceedingJoinPoint 获取 MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //2. 使用 MethodSignature 获取当前被注解的 Method
        Method method = signature.getMethod();

        // 3. 从 Method 中提取 LogExecution 注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        //4. 从LogExecution 注解中获取 description 属性
        return apiOperationLog.description();
    }
    /**
     * 转 JSON 字符串
     * @return
     */
    private Function<Object, String> toJsonStr(){
        return JsonUtils::toJsonString;
    }
}
