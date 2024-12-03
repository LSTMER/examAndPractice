package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-12-03 - 2024 12 03 15:57
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSend {
    private Question[] exam_questionlist;
    private Integer questionNum;
}
