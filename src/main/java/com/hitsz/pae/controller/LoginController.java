package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:41
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.LoginInfo;
import com.hitsz.pae.pojo.Result;
import com.hitsz.pae.pojo.Student;
import com.hitsz.pae.sevice.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    StudentService studentService;
    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        log.info("学员登录中......");
        LoginInfo login = studentService.Login(student);
        if(login != null) {
            return Result.success(login);
        }else{
            return Result.error("用户名或密码错误");
        }
    }
}
