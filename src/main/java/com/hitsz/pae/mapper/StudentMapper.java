package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 18:04
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.admin.AdminiGetStudInfo;
import com.hitsz.pae.pojo.stu.StuInfo;
import com.hitsz.pae.pojo.stu.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    /*登录业务*/
    // @ApiOperationLog(description = "数据库查询")
    Student login(Student student);

    /*查询学员科目*/
    List<Integer> selectProfession(Integer stuId);

    /*在管理员端进行搜索学员信息*/
    List<StuInfo> selectStuInfo(AdminiGetStudInfo adminiGetStudInfo);

    /*通过身份信息查询学员id 已使用索引*/
    Integer selectByIdCard(String idCard);

    /*插入学员信息    用于表格录入*/
    void insertStudent(Student student);

    /*更新学员信息    用于表格更新*/
    void updateStudent(Student student);

    /*插入学员的科目   用于表格更新*/
    void insertProfession(Integer id, List<Integer> professions);

    /*删除学员的科目   用于表格更新*/
    void delProfession(Integer id);


    /*暂时未开发使用*/
    void deleteStudent(Integer id);

    void deleteStudents(List<Integer> ids);

}
