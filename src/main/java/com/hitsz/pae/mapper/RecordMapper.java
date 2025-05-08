package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:39
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.exam.InfoExam;
import com.hitsz.pae.pojo.practice.PracticeListRefresh;
import com.hitsz.pae.pojo.practice.PracticeRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    /*以下是题单的记录插入*/

    /*根据id查询当前学员的题单完成情况，返回List为学员完成的所有题单*/
    @Select("select quesion_list_index from examandpratice.info_question_list where stu_id=#{id} and profession = #{profession}")
    List<Integer> selectListByProfession(@Param("id") Integer id, @Param("profession") Integer profession);

    @Select("select * from info_practice where stu_id = #{id} and question_id = #{questionId}")
    PracticeRecord selectPracticeRecordById(Integer id, Integer questionId);

    /*根据id和question的id，插入一条学员答案*/
    @Insert("insert into info_practice (stu_id, question_id, correct, s_answer) VALUES (#{stuId} ,#{questionId} ,#{correct}, #{sAnswer} )")
    void insertPracticeRecord(Integer questionId, Integer stuId, String sAnswer, boolean correct);

    void saveExamRecord(InfoExam infoExam);

    List<Integer> selectExamScore(Integer id, Integer profession);

    @Delete("delete from info_exams where stu_id = #{id}")
    void deleteExamRecord(Integer id);

    @Delete("delete from info_practice where stu_id = #{id}")
    void deletePracticeRecord(Integer id);

    void insertPracticeListRecord(PracticeListRefresh practiceListRefresh);

    Boolean findQuestionListId(Integer id, Integer profession);
}
