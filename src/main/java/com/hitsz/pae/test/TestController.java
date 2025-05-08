package com.hitsz.pae.test;/*
 *@Author:Simon
 *@Date: 2025-05-08 - 2025 05 08 17:23
 *@Description:practiceAndExam
 *@version:1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Value("${rate-limit.api.limit}")
    private Integer limit;
    @GetMapping("/test")
    public String test(){
        log.info("test");
        return "当前限流阈值为: " + limit;
    }
}
