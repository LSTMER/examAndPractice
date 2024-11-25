package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:53
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExamInfo {
    private Integer ExamIndex;
    private Integer profession;
}
