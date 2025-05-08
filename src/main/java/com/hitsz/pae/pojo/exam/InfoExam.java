package com.hitsz.pae.pojo.exam;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:54
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*记录了学员某一次测试的答题情况*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoExam {
    private Integer id;         //答题记录id
    private Integer userId;     //作答人id
    private boolean status;     //是否及格
    private Integer profession; //考试科目
    private Integer correctNum; //正确数量
    private Boolean flag;
}
