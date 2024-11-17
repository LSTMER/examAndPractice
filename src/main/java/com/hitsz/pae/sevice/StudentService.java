package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:49
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.LoginInfo;
import com.hitsz.pae.pojo.Student;

public interface StudentService {
    LoginInfo Login(Student stu);
}
