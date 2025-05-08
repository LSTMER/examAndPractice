package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:51
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.*;
import com.hitsz.pae.pojo.exam.ExamRecord;
import com.hitsz.pae.pojo.exam.GetExamInfo;
import com.hitsz.pae.pojo.exam.InfoExam;

public interface ExamService {
    ExamRecord[] getExamSurface(Integer id, Boolean flag);
    Question[] getExamQuestions(GetExamInfo examInfo);
    void saveExamRecord(InfoExam infoExam);
}
