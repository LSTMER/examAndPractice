package com.hitsz.pae.pojo.practice;/*
 *@Author:Simon
 *@Date: 2024-12-12 - 2024 12 12 12:32
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeRecord {
    private Integer stuId;
    private Integer questionId;
    private Boolean correct;
    private String sAnswer;
}
