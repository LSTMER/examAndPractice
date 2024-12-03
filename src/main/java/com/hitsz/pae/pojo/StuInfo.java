package com.hitsz.pae.pojo;/*
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
    private int id;
    private LocalDate date;
    private String name;
    private Integer profession;
    private List<Integer> score;
    private boolean flag;

    public boolean getFlag() {
        return flag;
    }
}
