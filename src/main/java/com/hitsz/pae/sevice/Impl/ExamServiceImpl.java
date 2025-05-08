package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:52
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.*;
import com.hitsz.pae.operationlog.aspect.ApiOperationLog;
import com.hitsz.pae.pojo.*;
import com.hitsz.pae.pojo.exam.ExamRecord;
import com.hitsz.pae.pojo.exam.GetExamInfo;
import com.hitsz.pae.pojo.exam.InfoExam;
import com.hitsz.pae.sevice.ExamService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    LilunRecordMapper lilunRecordMapper;

    @Resource
    LilunQuestionMapper lilunQuestionMapper;
    /*需要遍历当前学员的所有工种下的所有测试
     * 1：先查询当前学员的工种信息
     * 2：根据当前学员的工种信息查询对应测试的完成情况，及格情况
     * 3：
     * 4：
     * 5：查询成功后，填入对应的arr中
     */
    @Override
    public ExamRecord[] getExamSurface(Integer id, Boolean flag) {
        ExamRecord[] arr = new ExamRecord[Constant.NUM_OF_PROFESSION];
        List<Integer> professions = studentMapper.selectProfession(id);
        /*根据id查询当前工种信息*/
        if (flag) {
            for(Integer profession : professions) {
                /*查询当前学员当前工种下，测试记录*/
                int sum = questionMapper.countExamByProfessionAndId(id, profession);
                int pass = questionMapper.countExamByProfessionAndStatus(id, profession);
                arr[profession] = new ExamRecord(sum, pass);
                /*查询当前学员的完成情况：*/
            }
        }else{
            for(Integer profession : professions) {
                /*查询当前学员当前工种下，测试记录*/
                int sum = lilunQuestionMapper.countExamByProfessionAndId(id, profession);
                int pass = lilunQuestionMapper.countExamByProfessionAndStatus(id, profession);
                arr[profession] = new ExamRecord(sum, pass);
                /*查询当前学员的完成情况：*/
            }
        }
        return arr;
    }
    /*首先，根据学员id和profession 可以确定当前是第几次考试(这里好像不需要了，前端也不传递）直接用元组数好了*/

    @Override
    @ApiOperationLog(description = "获取题目")
    public Question[] getExamQuestions(GetExamInfo examInfo) {
        Question[] questions;

        if(examInfo.getFlag()){
            Set<Integer> set = getRandom(Constant.professionNumber(examInfo.getProfession()),questionMapper.countQuestionByProfession(examInfo.getProfession()));
            questions = questionMapper.getQuestionSet(set);
            // questions = questionMapper.selectRandomProfession(examInfo.getProfession(),Constant.professionNumber(examInfo.getProfession()));
        }
        else{
            Set<Integer> set = getRandom(Constant.professionNumber(examInfo.getProfession()),lilunQuestionMapper.countQuestionByProfession(examInfo.getProfession()));
            questions = lilunQuestionMapper.getQuestionSet(set);
        }
        return questions;
    }

    @Override
    public void saveExamRecord(InfoExam infoExam) {
        int correct = infoExam.getCorrectNum();
        int pass = Constant.professionGood(infoExam.getProfession());
        boolean status = correct >= pass;
        infoExam.setStatus(status);
        if(infoExam.getFlag()){
            recordMapper.saveExamRecord(infoExam);
        }else{
            lilunRecordMapper.saveExamRecord(infoExam);
        }
    }

    public Set<Integer> getRandom(int n, int scale){
        Set<Integer> set = new HashSet<>();
        if(n>scale){
            System.out.println("n>scale");
            return null;
        }
        if(n==scale){
            for(int i=0;i<n;i++){
                set.add(i);
            }
        }
        Random random = new Random();
        while(set.size()<n){
            int num = random.nextInt(scale);
            set.add(num);
        }
        return set;
    }
}
