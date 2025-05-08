package com.hitsz.pae.exceptions;/*
 *@Author:Simon
 *@Date: 2025-02-23 - 2025 02 23 23:45
 *@Description:practiceAndExam
 *@version:1.0
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface){
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}