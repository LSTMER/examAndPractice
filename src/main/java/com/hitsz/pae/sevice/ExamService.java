package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:51
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.*;

public interface ExamService {
    ExamRecord[] getExamSurface(Integer id);
    Question[] getExamQuestions(GetExamInfo examInfo);
    void saveExamRecord(Info_exam infoExam);
}
