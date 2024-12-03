package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 18:04
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.GetStuInfo;
import com.hitsz.pae.pojo.StuInfo;
import com.hitsz.pae.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    Student login(Student student);

    List<Integer> selectProfession(Integer stuId);

    List<StuInfo> selectStuInfo(GetStuInfo getStuInfo);
}
