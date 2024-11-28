package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 17:17
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {

    @Select("select * from administrator where phone = #{phone} and password = #{password}")
    public Administrator checkAdministrator(Administrator administrator);
}
