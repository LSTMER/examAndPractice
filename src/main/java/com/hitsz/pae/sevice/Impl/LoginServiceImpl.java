package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:50
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.AdministratorMapper;
import com.hitsz.pae.mapper.SessionMapper;
import com.hitsz.pae.mapper.StudentMapper;
import com.hitsz.pae.pojo.Administrator;
import com.hitsz.pae.pojo.StuLoginInfo;
import com.hitsz.pae.pojo.Session;
import com.hitsz.pae.pojo.Student;
import com.hitsz.pae.sevice.LoginService;
import com.hitsz.pae.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AdministratorMapper administratorMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SessionMapper sessionMapper;

    @Override
    @Transactional
    public StuLoginInfo studentLogin(Student stu) {
        Student student = studentMapper.login(stu);
        if(student != null) {
            //生成jwt令牌
            student.setProfession(studentMapper.selectProfession(student.getId()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",student.getId());
            claims.put("username",student.getName());
            claims.put("phone",student.getPhone());
            claims.put("profession",student.getProfession());
            /*调用工具类，生成jwt令牌*/
            String jwt = JwtUtils.generateJwt(claims);
            /*使用sessionMapper查询对应学员是否生成了token，若已经生成，则将原有的进行删除，再更新为新的token*/

            Session session = sessionMapper.findByPhone(student.getPhone());
            if(session != null) {
                sessionMapper.deleteByUserId(student.getPhone());
            }
            /*更新token，在表中插入，若不存在，同样插入*/
            session = new Session(student.getPhone(),jwt);
            sessionMapper.insert(session);
            return new StuLoginInfo(student.getId(),student.getName(),student.getPhone(),student.getProfession(),jwt);
        }
        return null;
    }

    /*查询管理员的账号密码信息是否正确，返回一个token*/
    @Override
    public String administratorLogin(Administrator admin) {
        /*先进行查询，账号密码是否正确*/
        Administrator administrator = administratorMapper.checkAdministrator(admin);
        if(administrator != null) {
            /*查询成功 生成jwt令牌*/
            Map<String, Object> claims = new HashMap<>();
            claims.put("phone",administrator.getPhone());
            String jwt = JwtUtils.generateJwt(claims);

            Session session = sessionMapper.findByPhone(administrator.getPhone());
            if(session != null) {
                sessionMapper.deleteByUserId(administrator.getPhone());
            }
            session = new Session(administrator.getPhone(),jwt);
            sessionMapper.insert(session);
            return jwt;
        }
        return null;
    }
}
