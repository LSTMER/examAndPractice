package com.hitsz.pae.pojo.exam;/*
 *@Author:Simon
 *@Date: 2024-12-12 - 2024 12 12 14:33
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.ListToQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPractice {
    private Integer id;
    private ListToQuestion listToQuestion;
    private Boolean flag;
}
