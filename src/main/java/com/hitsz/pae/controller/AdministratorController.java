package com.hitsz.pae.controller;/*
 *@Author:Simon
 *@Date: 2024-11-20 - 2024 11 20 14:13
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.pojo.admin.AdminiGetStudInfo;
import com.hitsz.pae.pojo.Result;
import com.hitsz.pae.pojo.stu.StuInfo;
import com.hitsz.pae.sevice.AdministratorService;
import com.hitsz.pae.sevice.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/administrator")
public class AdministratorController {
    /*管理员页面，可以添加学员，添加题目，删除学员，查看学员做题情况*/
    @Autowired
    AdministratorService administratorService;

    @Autowired
    FileService fileService;
    /*
    * 每次需要核实token，在header中传递。
    * 学员搜索接口：
    * 1 展示数据：
    *       private int id;
            private LocalDate date;
            private String name;
            private Integer profession;
            private List<Integer> score;
            private boolean flag;
    */

    // @ApiOperationLog(description = "管理员端学员搜索")
    @PostMapping("/search")
    public Result queryStudent(@RequestBody AdminiGetStudInfo adminiGetStudInfo){
        log.info("getStuInfo"+ adminiGetStudInfo);
        List<StuInfo> stuInfos = administratorService.queryStudent(adminiGetStudInfo);
        StuInfo[] stuInfoArray = new StuInfo[stuInfos.size()];
        stuInfos.toArray(stuInfoArray);
        return Result.success(stuInfoArray);
    }

    // @ApiOperationLog(description = "文件上传接口")
    @PostMapping("/file")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        // 调用服务层方法处理文件
        fileService.uploadFile(file);
        return Result.success();

    }
}
