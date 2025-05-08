package com.hitsz.pae.pojo.exam;/*
 *@Author:Simon
 *@Date: 2024-12-03 - 2024 12 03 15:57
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSend {
    private Question[] exam_questionlist;   //将考试的题目打包成一个数组发送到前端
    private Integer questionNum;            //将本次考试的题目数量传递给前端
}
