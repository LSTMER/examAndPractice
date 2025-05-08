package com.hitsz.pae.exceptions;/*
 *@Author:Simon
 *@Date: 2025-02-23 - 2025 02 23 23:45
 *@Description:practiceAndExam
 *@version:1.0
 */

public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
