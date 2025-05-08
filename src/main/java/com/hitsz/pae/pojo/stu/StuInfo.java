package com.hitsz.pae.pojo.stu;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 18:49
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuInfo {
    private int id;             //学员id
    private LocalDate date;     //学员考试日期
    private String name;        //学员名字
    private Integer profession; //学员对应工种
    private List<Integer> score;//学员某工种所有考试分数
    private boolean flag;       //学员多次考试是否优秀情况

    public boolean getFlag() {
        return flag;
    }
}
