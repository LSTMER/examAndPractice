package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:49
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Administrator;
import com.hitsz.pae.pojo.StuLoginInfo;
import com.hitsz.pae.pojo.Student;

public interface LoginService {
    StuLoginInfo studentLogin(Student stu);
    String administratorLogin(Administrator admin);
}
