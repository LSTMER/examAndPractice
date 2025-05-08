package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:41
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.*;
import com.hitsz.pae.pojo.admin.Administrator;
import com.hitsz.pae.pojo.admin.AdministratorInfo;
import com.hitsz.pae.pojo.stu.StuLoginInfo;
import com.hitsz.pae.pojo.stu.Student;
import com.hitsz.pae.sevice.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login/{flag}")
    // @ApiOperationLog(description = "登录接口")
    public Result login(@RequestBody Student student, @PathVariable Integer flag) {
        log.info("学员登录中......");
        StuLoginInfo login = loginService.studentLogin(student, flag);
        if(login != null) {
            log.info("登录成功");
            return Result.success(login);
        }else{
            log.info("学员用户名或密码错误");
            return Result.error("学员用户名或密码错误");
        }
    }

    @PostMapping("/adminLogin")
    public Result administratorLogin(@RequestBody Administrator administrator) {
        log.info("管理员登录中......");
        AdministratorInfo administratorInfo = loginService.administratorLogin(administrator);
        if(administratorInfo != null) {
            log.info("登录成功");
            return Result.success(administratorInfo);
        }else{
            log.info("管理员用户名或密码错误");
            return Result.error("管理员用户名或密码错误");
        }
    }
}
