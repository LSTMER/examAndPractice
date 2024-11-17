package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:50
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.mapper.StudentMapper;
import com.hitsz.pae.pojo.LoginInfo;
import com.hitsz.pae.pojo.Student;
import com.hitsz.pae.sevice.StudentService;
import com.hitsz.pae.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    @Transactional
    public LoginInfo Login(Student stu) {
        Student student = studentMapper.login(stu);
        if(student != null) {
            //生成jwt令牌
            student.setProfession(studentMapper.selectProfession(student.getId()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",stu.getId());
            claims.put("username",student.getName());
            claims.put("phone",student.getPhone());
            claims.put("profession",student.getProfession());
            /*调用工具类，生成jwt令牌*/
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(student.getId(),student.getName(),student.getPhone(),student.getProfession(),jwt);
        }
        return null;
    }
}
