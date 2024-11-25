package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:29
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info_practice {
    private Integer userId;//作答人id
    private Integer questionIndex;//题单中的第index道题目
    private Integer questionListIndex;//题单号
    private String sAnswer;//学员的作答
    private Integer profession;//题单的题型
}
