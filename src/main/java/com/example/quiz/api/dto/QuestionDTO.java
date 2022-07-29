package com.example.quiz.api.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private Long id;
    private String title;
    private List<AnswerDTO> answers = new ArrayList<>();

    public QuestionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public void addAnswerDTO(AnswerDTO answerDTO){
        answers.add(answerDTO);
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answers=" + answers +
                '}';
    }
}
