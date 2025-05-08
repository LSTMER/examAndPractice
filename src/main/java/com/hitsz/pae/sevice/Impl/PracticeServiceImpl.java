package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:24
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.*;
import com.hitsz.pae.pojo.*;
import com.hitsz.pae.pojo.exam.GetPractice;
import com.hitsz.pae.pojo.practice.ConfirmPractice;
import com.hitsz.pae.pojo.practice.InfoPractice;
import com.hitsz.pae.pojo.practice.PracticeListRefresh;
import com.hitsz.pae.pojo.practice.PracticeRecord;
import com.hitsz.pae.sevice.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    LilunQuestionMapper lilunQuestionMapper;

    @Autowired
    LilunRecordMapper lilunRecordMapper;
    /*需要遍历当前学员的所有工种下的所有题单
    * 1：先查询当前学员的工种信息
    * 2：根据当前学员的工种信息查询对应题单
    * 3：需要查询当前工种有多少个题单
    * 4：对题单信息中的对应学员完成的题单进行查询
    * 5：查询成功后，填入对应的arr中
    */
    @Override
    public boolean[][] getPracticeSurface(Integer id, Boolean flag) {
        /*根据id查询当前工种信息*/
        boolean[][] arr = new boolean[Constant.NUM_OF_PROFESSION][];
        List<Integer> professions = studentMapper.selectProfession(id);
        if (flag) {
            for(Integer profession : professions){
                /*查询当前工种的题单数量：*/
                int sum = questionMapper.countListByProfession(profession);
                arr[profession] = new boolean[sum];
                /*查询当前学员的完成情况：*/
                List<Integer> finishes = recordMapper.selectListByProfession(id,profession);
                System.out.println(finishes);
                for(Integer finish : finishes){
                    arr[profession][finish] = true;
                }
            }
        }else{
            for(Integer profession : professions){
                /*查询当前工种的题单数量：*/
                int sum = lilunQuestionMapper.countListByProfession(profession);
                arr[profession] = new boolean[sum];
                /*查询当前学员的完成情况：*/
                List<Integer> finishes = lilunRecordMapper.selectListByProfession(id,profession);
                for(Integer finish : finishes){
                    arr[profession][finish] = true;
                }
            }
        }
        return arr;
    }

    @Override
    public InfoPractice getQuestionByListIndex(GetPractice getPractice) {
        Question question;
        PracticeRecord practiceRecord;
        if(getPractice.getFlag()){
            log.info(getPractice+"");
            Integer id = questionMapper.selectByListInfo(getPractice.getListToQuestion());
            question = questionMapper.selectById(id);
            practiceRecord = recordMapper.selectPracticeRecordById(getPractice.getId(),id);
        }else {
            Integer id = lilunQuestionMapper.selectByListInfo(getPractice.getListToQuestion());
            question = lilunQuestionMapper.selectById(id);
            practiceRecord = lilunRecordMapper.selectPracticeRecordById(getPractice.getId(),id);
        }
        InfoPractice infoPractice = new InfoPractice();
        infoPractice.setQuestion(question);
        infoPractice.setRecord(practiceRecord);
        return infoPractice;
    }

    @Override
    public void savePracticeRecord(ConfirmPractice confirmPractice) {
        if (confirmPractice.getFlag()) {
            Integer questionId = questionMapper.selectByListInfo(new ListToQuestion(confirmPractice.getQuestionListIndex(),confirmPractice.getQuestionIndex(),confirmPractice.getProfession()));
            /*判断学员是否作答正确*/
            String cAnswer = questionMapper.selectAnswerByQuestionId(questionId);
            boolean correct = confirmPractice.getS_answer().equals(cAnswer);
            recordMapper.insertPracticeRecord(questionId,confirmPractice.getUserId(),confirmPractice.getS_answer(),correct);
        }else{
            Integer questionId = lilunQuestionMapper.selectByListInfo(new ListToQuestion(confirmPractice.getQuestionListIndex(),confirmPractice.getQuestionIndex(),confirmPractice.getProfession()));
            /*判断学员是否作答正确*/
            String cAnswer = lilunQuestionMapper.selectAnswerByQuestionId(questionId);
            boolean correct = confirmPractice.getS_answer().equals(cAnswer);
            lilunRecordMapper.insertPracticeRecord(questionId,confirmPractice.getUserId(),confirmPractice.getS_answer(),correct);
        }
    }
    /*通过题单，题型，题单中的index，返回一个对应的题目*/

    @Override
    public void refreshPracticeListRecord(PracticeListRefresh practiceListRefresh) {
        /*检查当前的题单是否完成*/
        System.out.println(practiceListRefresh);
        if(practiceListRefresh.getFlag()){
            Boolean flag = recordMapper.findQuestionListId(practiceListRefresh.getQuestionListId(), practiceListRefresh.getProfession());
            log.info(String.valueOf(flag));
            if(flag==null){
                recordMapper.insertPracticeListRecord(practiceListRefresh);
            }
        }else{
            Boolean flag = lilunQuestionMapper.findQuestionListId(practiceListRefresh.getQuestionListId());
            log.info(String.valueOf(flag));
            if (flag==null) {
                lilunRecordMapper.insertPracticeListRecord(practiceListRefresh);
            }
        }
    }

}
