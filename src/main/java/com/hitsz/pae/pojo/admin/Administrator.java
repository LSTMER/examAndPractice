package com.hitsz.pae.pojo.admin;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 16:26
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    private String phone;       //管理员登录账号
    private String password;    //管理员登录密码
    private Integer id;
}
