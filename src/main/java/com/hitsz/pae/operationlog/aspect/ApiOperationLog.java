package com.hitsz.pae.operationlog.aspect;/*
 *@Author:Simon
 *@Date: 2024-12-16 - 2024 12 16 20:08
 *@Description:xiaohashu
 *@version:1.0
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}
