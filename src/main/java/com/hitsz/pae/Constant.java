package com.hitsz.pae;/*
 *@Author:Simon
 *@Date: 2024-11-17 - 2024 11 17 21:52
 *@Description:praticeAndExam
 *@version:1.0
 */

public class Constant {
    public static final int NUM_OF_PROFESSION = 4;
    public static final int NUM_OF_EXAM_QUESTION_NUM_10 = 10;
    public static final int NUM_OF_EXAM_QUESTION_NUM_20 = 20;

    public static int professionNumber(Integer profession){
        if(profession==2){
            return NUM_OF_EXAM_QUESTION_NUM_10;
        }else{
            return NUM_OF_EXAM_QUESTION_NUM_20;
        }
    }

    public static int professionExcellent(Integer profession){
        if(profession==2){
            return 8;
        }else{
            return 16;
        }
    }

    public static int professionGood(Integer profession){
        if(profession==2){
            return 6;
        }
        else{
            return 12;
        }
    }
}
