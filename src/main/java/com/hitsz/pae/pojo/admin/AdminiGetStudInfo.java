package com.hitsz.pae.pojo.admin;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 18:44
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

/*本类是用于管理员端口的模糊查询*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminiGetStudInfo {
    private YearMonth date;         //学员的考试日期
    private String name;            //学员名字
    private Integer profession;     //学员的工种
    private Boolean flag;           //是否及格
    private Integer id;             //管理员的id

    private Boolean type;           //查询的是科目三还是理论
}
