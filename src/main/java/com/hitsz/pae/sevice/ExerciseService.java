package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:24
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.GetListInfo;
import com.hitsz.pae.pojo.Info_practice;
import com.hitsz.pae.pojo.Question;
import org.springframework.stereotype.Service;

@Service
public interface ExerciseService {
    boolean[][] getPracticeSurface(Integer id);
    Question getQuestionByListIndex(GetListInfo listInfo);
    void savePracticeRecord(Info_practice infoP);
}
