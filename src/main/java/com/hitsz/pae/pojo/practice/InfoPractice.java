package com.hitsz.pae.pojo.practice;

import com.hitsz.pae.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 记录学生答题情况
* @TableName info_pratice
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPractice {
    private Integer id;
    private Integer profession;

    private Boolean correct;
    private String s_answer;
    private String c_answer;

    private String questionPicture;
    private String questionScript;
    private String optionAl;

    private boolean flagContain;
    private boolean flagMul;

    public void setQuestion(Question question) {
        this.id = question.getId();
        this.profession = question.getProfession();
        this.c_answer = question.getCAnswer();
        this.questionPicture = question.getQuestionPicture();
        this.questionScript = question.getQuestionScript();
        this.optionAl = question.getOptionAl();
        this.flagContain = question.isFlagContain();
        this.flagMul = question.isFlagMul();
    }
    public void setRecord(PracticeRecord practiceRecord) {
        if(practiceRecord != null) {
            s_answer = practiceRecord.getSAnswer();
            correct = practiceRecord.getCorrect();
        }else{
            s_answer = null;
            correct = null;
        }
    }
}
