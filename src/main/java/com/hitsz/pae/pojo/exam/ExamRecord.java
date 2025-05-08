package com.hitsz.pae.pojo.exam;/*
 *@Author:Simon
 *@Date: 2024-11-21 - 2024 11 21 17:12
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*该类用于展示学员考试记录信息*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRecord {
    private Integer doneNum;    //前端返回本次考试有多少题目
    private Integer passNum;    //本次考试做对了多少道题目
}
