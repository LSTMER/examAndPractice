package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 20:56
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionList {
    private Integer id;
    private Integer questionListIndex;
    private Integer profession;
}
