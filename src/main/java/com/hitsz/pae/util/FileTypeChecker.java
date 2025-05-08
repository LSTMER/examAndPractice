package com.hitsz.pae.util;/*
 *@Author:Simon
 *@Date: 2025-03-04 - 2025 03 04 21:12
 *@Description:practiceAndExam
 *@version:1.0
 */

import org.springframework.web.multipart.MultipartFile;

public class FileTypeChecker {
    public static boolean isCsv(MultipartFile file){
        return file.getOriginalFilename()!=null&&
                (file.getOriginalFilename().toLowerCase().endsWith(".csv"));
    }

    public static boolean isXLSX(MultipartFile file){
        return file.getOriginalFilename()!=null&&
                (file.getOriginalFilename().toLowerCase().endsWith("xlsx"));
    }

    public static boolean isXLS(MultipartFile file){
        return file.getOriginalFilename()!=null&&
                (file.getOriginalFilename().toLowerCase().endsWith(".xls"));
    }
}
