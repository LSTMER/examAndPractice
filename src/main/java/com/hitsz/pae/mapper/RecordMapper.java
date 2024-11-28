package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:39
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Info_exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {

    /*以下是题单的记录插入*/

    /*根据id查询当前学员的题单完成情况，返回List为学员完成的所有题单*/
    @Select("select quesion_list_index from examandpratice.info_question_list where stu_id=#{id} and profession = #{profession}")
    List<Integer> selectListByProfession(@Param("id") Integer id, @Param("profession") Integer profession);

    /*根据id和question的id，插入一条学员答案*/
    @Insert("insert into info_pratice (stu_id, question_id, correct, s_answer) VALUES (#{userId} ,#{questionId} ,#{correct}, #{sAnswer} )")
    void insertPracticeRecord(Integer questionId, Integer stuId, String sAnswer, boolean correct);

    void saveExamRecord(Info_exam infoExam);
}
