package com.hitsz.pae.exception;/*
 *@Author:Simon
 *@Date: 2024-10-19 - 2024 10 19 11:00
 *@Description:web-project-actual-combat
 *@version:1.0
 */

import com.hitsz.pae.exceptions.BizException;
import com.hitsz.pae.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ BizException.class })
    @ResponseBody
    public Result handleBizException(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Result.error(e);
    }
    /*捕获exception，并向前端返回错误信息*/
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("unhandle error",e);
        return Result.error(e.getMessage());
    }
}
