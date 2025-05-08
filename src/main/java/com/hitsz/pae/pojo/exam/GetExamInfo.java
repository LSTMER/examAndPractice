package com.hitsz.pae.pojo.exam;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 11:53
 *@Description:praticeAndExam
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*这里是保存了前端送来的id和profession*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExamInfo {
    private Integer id;         //保存前端传递的id和profession信息，准备查找该信息下的测试题目
    private Integer profession; //发现这个id根本没有必要存在。。。
    private Boolean flag;
}
