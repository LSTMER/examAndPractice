package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-21 - 2024 11 21 17:12
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*该类用于记录学员考试记录信息*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRecord {
    private Integer doneNum;
    private Integer passNum;
}
