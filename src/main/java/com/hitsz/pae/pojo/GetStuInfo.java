package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 18:44
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStuInfo {
    private LocalDate date;
    private String name;
    private Integer profession;
    private boolean flag;
}
