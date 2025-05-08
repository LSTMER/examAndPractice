package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:21
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.*;
import com.hitsz.pae.pojo.exam.GetPractice;
import com.hitsz.pae.pojo.practice.ConfirmPractice;
import com.hitsz.pae.pojo.practice.InfoPractice;
import com.hitsz.pae.pojo.practice.PracticeList;
import com.hitsz.pae.pojo.practice.PracticeListRefresh;
import com.hitsz.pae.sevice.PracticeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PracticeController {

    @Autowired
    PracticeService practiceService;
    /*
    * 查询学员的题单完成情况：展示在练习界面中*/
    @GetMapping("/exercise/{id}/{flag}")
    public Result getExerciseSurface(@PathVariable Integer id, @PathVariable Boolean flag) {
        log.info("getExerciseSurface from "+id);
        PracticeList practiceList = new PracticeList(practiceService.getPracticeSurface(id,flag));
        return Result.success(practiceList);
    }

    /*在练习页面发送题目*/
    @PostMapping("/question/send")
    public Result getQuestionInList(@RequestBody GetPractice getPractice) {
        log.info("getQuestionInList from {}", getPractice);
        InfoPractice infoPractice = practiceService.getQuestionByListIndex(getPractice);
        return Result.success(infoPractice);
    }

    /*在练习接口收到学生作答，插入作答记录*/
    @PostMapping("/question/confirm")
    public Result confirmQuestion(@RequestBody ConfirmPractice confirm_practice) {
        log.info("confirmQuestion from {}", confirm_practice);
        practiceService.savePracticeRecord(confirm_practice);
        return Result.success();
    }

    @PostMapping("/question/refresh")
    public Result refreshPracticeList(@RequestBody PracticeListRefresh practiceListRefresh){
        log.info("refreshPracticeList from {}", practiceListRefresh);
        practiceService.refreshPracticeListRecord(practiceListRefresh);
        return Result.success();
    }
}
