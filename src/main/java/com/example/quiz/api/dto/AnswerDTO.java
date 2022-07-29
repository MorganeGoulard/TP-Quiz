package com.example.quiz.api.dto;

import com.example.quiz.business.Answer;
import com.example.quiz.business.Question;

public class AnswerDTO {

    private Long id;
    private String proposition;


    public AnswerDTO() {
    }

    public AnswerDTO(Answer answer){
        this.id= answer.getId();
        this.proposition= answer.getProposition();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "id=" + id +
                ", proposition='" + proposition + '\'' +
                '}';
    }
}
