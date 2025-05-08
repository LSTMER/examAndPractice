package com.hitsz.pae.sevice;/*
 *@Author:Simon
 *@Date: 2025-03-04 - 2025 03 04 20:50
 *@Description:practiceAndExam
 *@version:1.0
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void uploadFile(MultipartFile file);
}
