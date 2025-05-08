package com.hitsz.pae.pojo.practice;/*
 *@Author:Simon
 *@Date: 2025-02-20 - 2025 02 20 14:11
 *@Description:practiceAndExam
 *@version:1.0
 */

import lombok.Data;

@Data
public class PracticeListRefresh {
    private Integer id;
    private Integer questionListId;
    private Integer profession;
    private Boolean flag;
}
