package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-16 - 2024 11 16 16:50
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.*;
import com.hitsz.pae.pojo.admin.Administrator;
import com.hitsz.pae.pojo.admin.AdministratorInfo;
import com.hitsz.pae.pojo.stu.StuLoginInfo;
import com.hitsz.pae.pojo.stu.Student;
import com.hitsz.pae.sevice.LoginService;
import com.hitsz.pae.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AdministratorMapper administratorMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    LilunQuestionMapper lilunQuestionMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional
    public StuLoginInfo studentLogin(Student stu, Integer flag) {
        Student student = studentMapper.login(stu);
        /*若学生的登录信息验证成功，则将生成jwt令牌，录入数据库，并给前端传递所需要的信息*/
        if(student != null) {
            //生成jwt令牌
            student.setProfession(studentMapper.selectProfession(student.getId()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",student.getId());
            /*调用工具类，生成jwt令牌*/
            String jwt = JwtUtils.generateJwt(claims);
            /*使用sessionMapper查询对应学员是否生成了token，若已经生成，则将原有的进行删除，再更新为新的token*/
            asyncUpdateSessionJwt(String.valueOf(student.getId()),jwt);

            Integer[] temp = new Integer[Constant.NUM_OF_PROFESSION];
            if (flag==1) {
                for(Integer profession:student.getProfession()){
                    /*这里返回给前端学员对应科目下的题目总数，其中数组的下标对应了第几个科目*/
                    temp[profession] = questionMapper.countQuestionByProfession(profession);
                }
            }else{
                for(Integer profession:student.getProfession()){
                    temp[profession] = lilunQuestionMapper.countQuestionByProfession(profession);
                }
            }
            return new StuLoginInfo(student.getId(),student.getName(),student.getPhone(),temp,jwt);
        }
        return null;
    }

    /*查询管理员的账号密码信息是否正确，返回一个token*/
    @Override
    public AdministratorInfo administratorLogin(Administrator admin) {
        try {
            /*先进行查询，账号密码是否正确*/
            Administrator administrator = administratorMapper.checkAdministrator(admin);
            if(administrator != null) {
                /*查询成功 生成jwt令牌*/
                Map<String, Object> claims = new HashMap<>();
                claims.put("phone",administrator.getPhone());
                String jwt = JwtUtils.generateJwt(claims);
                // 异步更新Session中的JWT
                asyncUpdateSessionJwt(administrator.getPhone(), jwt);
                return new AdministratorInfo(administrator.getId(),jwt);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void asyncUpdateSessionJwt(String id, String jwt) {
        long expireSeconds = 60*60*24;
        redisTemplate.opsForValue().set(id, jwt, expireSeconds, TimeUnit.SECONDS);
    }
}
