package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 18:57
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.admin.AdminiGetStudInfo;
import com.hitsz.pae.pojo.stu.StuInfo;

import java.util.List;

public interface AdministratorService {
    List<StuInfo> queryStudent(AdminiGetStudInfo adminiGetStudInfo);
}
