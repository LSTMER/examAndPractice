package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 14:13
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.Administrator;
import com.hitsz.pae.pojo.GetStuInfo;
import com.hitsz.pae.pojo.Result;
import com.hitsz.pae.pojo.StuInfo;
import com.hitsz.pae.sevice.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("administrator")
public class AdministratorController {
    /*管理员页面，可以添加学员，添加题目，删除学员，查看学员做题情况*/
    @Autowired
    AdministratorService administratorService;

    /*
    * 每次需要核实token，在header中传递。
    time以月份为单位,具体格式如：2024-05 如为空，默认传输空字符串表示为当前月份
    name是特定搜索某个学员时才传输 默认传递空字符串
    profession是学员专业 ，用0-3进行传输 默认传输4表示所有工种
    flag 是一个选项，展示优秀次数不够3次的学生 默认传递false*/
//    @PostMapping
//    public Result addAdministrator(@RequestBody Administrator administrator) {
//        return Result.success(administrator);
//    }

    @PostMapping("/search")
    public Result queryStudent(@RequestBody GetStuInfo getStuInfo){
        List<StuInfo> stuInfos = administratorService.queryStudent(getStuInfo);
        StuInfo[] stuInfoArray = new StuInfo[stuInfos.size()];
        stuInfos.toArray(stuInfoArray);
        return Result.success(stuInfoArray);
    }
}
