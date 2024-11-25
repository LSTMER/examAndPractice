package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2024-11-19 - 2024 11 19 21:02
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListInfo {
    private Integer questionListIndex;
    private Integer questionIndex;
    private Integer profession;
}
