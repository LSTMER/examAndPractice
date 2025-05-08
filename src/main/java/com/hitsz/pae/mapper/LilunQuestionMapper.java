package com.hitsz.pae.mapper;/*
 *@Author:Simon
 *@Date: 2025-01-21 - 2025 01 21 16:14
 *@Description:practiceAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.ListToQuestion;
import com.hitsz.pae.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface LilunQuestionMapper {
    @Select("select count(*) from lilun_question where profession = #{profession}")
    int countQuestionByProfession(Integer profession);

    /*根据工种查询当前题单数量*/
    @Select("select count(*) from lilun_question_list where profession = #{profession}")
    int countListByProfession(Integer profession);

    /*根据题单号，工种号，题单对应的题目号，查询到一个题目id*/
    Integer selectByListInfo(ListToQuestion listToQuestion);

    /*根据题目的id，查询当前question*/
    Question selectById(Integer id);

    /*根据题目id，查询当前question的正确答案*/
    @Select("select c_answer from lilun_question where id = #{id}")
    String selectAnswerByQuestionId(Integer id);

    /*可以随机返回一条对应profession的题目*/
    Question[] selectRandomProfession(Integer profession, Integer number);

    /*需求更新：
     * 在exam中需要随机返回10或者20道题目，这里就可以直接调用上面的方法
     * 不需要再记录exam的题目id了
     * 只需要在保存的时候，记录下当前学员，当前学科，的分数，是否及格就可以了*/

    /*查询测试记录info_exam：返回
     * 通过学员的id和学员的profession，查询对应的记录
     * 1：查询总答题数
     * 2：查询合格数*/
    @Select("select count(*) from lilun_info_exams where stu_id = #{id} and profession = #{profession}")
    int countExamByProfessionAndId(Integer id, Integer profession);

    @Select("select count(*) from lilun_info_exams where stu_id = #{id} and profession = #{profession} and status = true")
    int countExamByProfessionAndStatus(Integer id, Integer profession);

    Boolean findQuestionListId(Integer questionListId);

    Question[] getQuestionSet(@Param("set") Set<Integer> set);
}
