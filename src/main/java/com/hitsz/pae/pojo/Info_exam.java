package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:54
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info_exam {
    private Integer userId;//作答人id
    private Integer ExamIndex;//测试号
    private String sAnswer;//学员的作答
    private Integer profession;//测试的题型
}
