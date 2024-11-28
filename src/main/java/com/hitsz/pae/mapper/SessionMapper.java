package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-21 - 2024 11 21 15:31
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Session;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SessionMapper {

    @Select("SELECT * FROM session WHERE phone = #{phone}")
    Session findByPhone(String phone);

    @Insert("INSERT INTO session (phone, token) VALUES (#{phone}, #{token})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Session session);

    @Delete("DELETE FROM session WHERE phone = #{phone}")
    void deleteByUserId(String phone);
}
