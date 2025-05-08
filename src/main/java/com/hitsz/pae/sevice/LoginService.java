package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:49
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.admin.Administrator;
import com.hitsz.pae.pojo.admin.AdministratorInfo;
import com.hitsz.pae.pojo.stu.StuLoginInfo;
import com.hitsz.pae.pojo.stu.Student;

public interface LoginService {
    StuLoginInfo studentLogin(Student stu, Integer flag);
    AdministratorInfo administratorLogin(Administrator admin);
}
