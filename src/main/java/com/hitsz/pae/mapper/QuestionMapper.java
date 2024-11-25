package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 20:59
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.GetExamInfo;
import com.hitsz.pae.pojo.GetListInfo;
import com.hitsz.pae.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper {
    /*根据工种查询当前题单数量*/
    @Select("select count(*) from examandpratice.question_list where profession = #{profession}")
    int countListByProfession(Integer profession);

    /*根据题单号，工种号，题单对应的题目号，查询到一个题目id*/
    Integer selectByListInfo(GetListInfo getListInfo);

    /*根据题目的id，查询当前question*/
    @Select("select * from examandpratice.question where id = #{id}")
    Question selectById(Integer id);

    @Select("select c_answer from question where id = #{id}")
    String selectAnswerByQuestionId(Integer id);

    @Select("select count(*) from exam where profession = #{profession}")
    int countExamByProfession(Integer profession);

    Integer selectByExamInfo(GetExamInfo getExamInfo);
    /*查询测试记录info_exam：返回
    * 通过学员的id和学员的profession，查询对应的记录
    * 1：查询总答题数
    * 2：查询合格数*/
    @Select("select count(*) from info_exams where stu_id = #{id} and profession = #{profession}")
    int countExamByProfessionAndId(Integer id, Integer profession);

    @Select("select count(*) from info_exams where stu_id = #{id} and profession = #{profession} and status = true")
    int countExamByProfessionAndStatus(Integer id, Integer profession);
}
