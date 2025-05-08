package com.hitsz.pae.pojo;/*
 *@Author:Simon
 *@Date: 2025-03-04 - 2025 03 04 21:46
 *@Description:practiceAndExam
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
public class UploadInfo {
    private LocalDate date;
    private String phone;
    private Integer id;
}
