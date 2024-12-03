package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2024-11-28 - 2024 11 28 18:58
 *@Description:praticeAndExam
 *@version:1.0
 */

import com.hitsz.pae.Constant;
import com.hitsz.pae.mapper.RecordMapper;
import com.hitsz.pae.mapper.StudentMapper;
import com.hitsz.pae.pojo.GetStuInfo;
import com.hitsz.pae.pojo.StuInfo;
import com.hitsz.pae.sevice.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<StuInfo> queryStudent(GetStuInfo getStuInfo) {
        List<StuInfo> stuInfos = studentMapper.selectStuInfo(getStuInfo);
        for(StuInfo stu : stuInfos){
            List<Integer> scores = recordMapper.selectExamScore(stu.getId(),stu.getProfession());
            stu.setScore(scores);
            stu.setFlag(Excellent(scores,stu.getProfession()));
        }
        if(getStuInfo.isFlag()){
            return stuInfos.stream().filter(stuInfo -> !stuInfo.getFlag()).toList();
        }
        return stuInfos;
    }

    private boolean Excellent(List<Integer> scores, Integer profession){
        int sum = 0;
        if(profession==null){
            return false;
        }
        int pass = Constant.professionExcellent(profession);
        for(Integer score : scores){
            if(score>=pass){
                sum++;
                if(sum>2){
                    return true;
                }
            }
        }
        return false;
    }
}
