package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 20:46
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private Integer profession;
    private String cAnswer;
    private String questionPicture;
    private String questionScript;
    private String option;

    private boolean flagContain;
    private boolean flagMul;
}
