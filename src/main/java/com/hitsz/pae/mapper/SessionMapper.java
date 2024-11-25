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

    @Select("SELECT * FROM session WHERE stu_id = #{sutId}")
    Session findByStuId(Integer stuId);

    @Insert("INSERT INTO session (id,stu_id, token) VALUES (0,#{stuId}, #{token})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Session session);

    @Delete("DELETE FROM session WHERE stu_id = #{stuId}")
    void deleteByUserId(Integer stuId);
}
