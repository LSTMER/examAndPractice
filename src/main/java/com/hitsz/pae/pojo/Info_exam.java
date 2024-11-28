package com.hitsz.pae.pojo;/*
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
public class Info_exam {
    private Integer id;
    private Integer userId;//作答人id
    private boolean status;
    private Integer profession;
    private Integer correctNum;
}
