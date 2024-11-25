package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:24
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.QuestionMapper;
import com.hitsz.pae.mapper.RecordMapper;
import com.hitsz.pae.mapper.StudentMapper;
import com.hitsz.pae.pojo.GetListInfo;
import com.hitsz.pae.pojo.Info_practice;
import com.hitsz.pae.pojo.Question;
import com.hitsz.pae.sevice.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RecordMapper recordMapper;
    /*需要遍历当前学员的所有工种下的所有题单
    * 1：先查询当前学员的工种信息
    * 2：根据当前学员的工种信息查询对应题单
    * 3：需要查询当前工种有多少个题单
    * 4：对题单信息中的对应学员完成的题单进行查询
    * 5：查询成功后，填入对应的arr中
    */
    @Override
    public boolean[][] getPracticeSurface(Integer id) {
        /*根据id查询当前工种信息*/
        boolean[][] arr = new boolean[Constant.NUM_OF_PROFESSION][];
        List<Integer> professions = studentMapper.selectProfession(id);
        for(Integer profession : professions){
            /*查询当前工种的题单数量：*/
            int sum = questionMapper.countListByProfession(profession);
            arr[profession] = new boolean[sum];
            /*查询当前学员的完成情况：*/
            List<Integer> finishes = recordMapper.selectListByProfession(id,profession);
            for(Integer finish : finishes){
                arr[profession][finish] = true;
            }
        }
        return arr;
    }

    @Override
    public Question getQuestionByListIndex(GetListInfo listInfo) {
        Integer id = questionMapper.selectByListInfo(listInfo);
        return questionMapper.selectById(id);
    }

    @Override
    public void savePracticeRecord(Info_practice infoP) {
        Integer questionId = questionMapper.selectByListInfo(new GetListInfo(infoP.getQuestionListIndex(),infoP.getQuestionIndex(),infoP.getProfession()));
        /*判断学员是否作答正确*/
        String cAnswer = questionMapper.selectAnswerByQuestionId(questionId);
        boolean correct = false;
        if(infoP.getSAnswer().equals(cAnswer)){
            correct = true;
        }
        recordMapper.insertPracticeRecord(questionId,infoP.getUserId(),infoP.getSAnswer(),correct);
    }

    /*通过题单，题型，题单中的index，返回一个对应的题目*/

}
