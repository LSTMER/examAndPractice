package com.hitsz.pae.pojo.stu;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:51
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String phone;
    private List<Integer> profession;
    private String idCard;
    private String password;
    private YearMonth date;
    private Integer adminId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
