package com.hitsz.pae.enums;/*
 *@Author:Simon
 *@Date: 2025-02-23 - 2025 02 23 23:47
 *@Description:practiceAndExam
 *@version:1.0
 */

import com.hitsz.pae.exceptions.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("AUTH-10000", "出错啦，后台正在努力修复中..."),
    PARAM_NOT_VALID("AUTH-10001", "参数错误"),

    // ----------- 业务异常状态码 -----------
    USER_NOT_FOUND("AUTH-20003", "该用户不存在"),
    PHONE_OR_PASSWORD_ERROR("AUTH-20004", "手机号或密码错误"),
    LOGIN_FAIL("AUTH-20005", "登录失败"),
    ;
    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;
}