package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:41
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Administrator;
import com.hitsz.pae.pojo.StuLoginInfo;
import com.hitsz.pae.pojo.Result;
import com.hitsz.pae.pojo.Student;
import com.hitsz.pae.sevice.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        log.info("学员登录中......");
        StuLoginInfo login = loginService.studentLogin(student);
        if(login != null) {
            return Result.success(login);
        }else{
            return Result.error("用户名或密码错误");
        }
    }

    @PostMapping("/adminLogin")
    public Result administratorLogin(@RequestBody Administrator administrator) {
        log.info("管理员登录中......");
        String token = loginService.administratorLogin(administrator);
        if(token != null) {
            return Result.success(token);
        }else{
            return Result.error("用户名或密码错误");
        }
    }
}
