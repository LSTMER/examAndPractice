package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 17:17
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.admin.Administrator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper {
    Administrator checkAdministrator(Administrator administrator);
}
