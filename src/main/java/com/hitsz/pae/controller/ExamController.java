package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:50
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.pojo.*;
import com.hitsz.pae.sevice.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    ExamService examService;
    /*
     * 查询学员的测试情况：展示在测试界面中
     * 1：返回学员工种下测试的次数
     * 2：返回学员工种下测试的通过次数
     * 3: 使用ExamRecord封装返回*/
    @GetMapping("/{id}")
    public Result getExamSurface(@PathVariable Integer id) {
        log.info("getExerciseSurface from "+id);
        ExamRecord[] status = examService.getExamSurface(id);
        return Result.success(status);
    }

    /*在测试页面，一次返回10或者20道题目，只需要一次的提交
    * 这里使用数组，将题一次性提交给前端*/
    @PostMapping("/send")
    public Result getQuestionInList(@RequestBody GetExamInfo getExamInfo) {
        log.info("getQuestionInList from "+getExamInfo);
        ExamSend examSend = new ExamSend();
        examSend.setExam_questionlist(examService.getExamQuestions(getExamInfo));
        examSend.setQuestionNum(Constant.professionNumber(getExamInfo.getProfession()));
        System.out.println(examSend);
        return Result.success(examSend);
    }

    /*在练习接口收到学生作答，插入作答记录*/
    @PostMapping("/confirm")
    public Result confirmQuestion(@RequestBody Info_exam infoExam) {
        log.info("confirmQuestion from "+infoExam);
        examService.saveExamRecord(infoExam);
        return Result.success();
    }
}
