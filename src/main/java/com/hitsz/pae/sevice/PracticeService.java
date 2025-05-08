package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:24
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.exam.GetPractice;
import com.hitsz.pae.pojo.practice.ConfirmPractice;
import com.hitsz.pae.pojo.practice.InfoPractice;
import com.hitsz.pae.pojo.practice.PracticeListRefresh;
import org.springframework.stereotype.Service;

@Service
public interface PracticeService {
    boolean[][] getPracticeSurface(Integer id,Boolean flag);
    InfoPractice getQuestionByListIndex(GetPractice getPractice);
    void savePracticeRecord(ConfirmPractice confirm_practice);
    void refreshPracticeListRecord(PracticeListRefresh practiceListRefresh);
}
