package com.hitsz.pae.exception;/*
 *@Author:Simon
 *@Date: 2024-10-19 - 2024 10 19 11:00
 *@Description:web-project-actual-combat
 *@version:1.0
 */

import com.hitsz.pae.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*捕获exception，并向前端返回错误信息*/
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }
}
