package com.example.quiz.api.dto;

import com.example.quiz.business.Answer;
import com.example.quiz.business.Question;

public class QuestionMapper {

    public static QuestionDTO convertToDTO(Question question){

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setTitle(question.getTitle());
        for(Answer answer : question.getAnswers() ){
            AnswerDTO answerDTO = AnswerMapper.convertToDTO(answer);
            questionDTO.addAnswerDTO(answerDTO);
        }

        return questionDTO;
    }

    public static Question convertToEntity(QuestionDTO questionDTO){

        Question question = new Question();

        question.setId(questionDTO.getId());
        question.setTitle(questionDTO.getTitle());
        for(AnswerDTO answerDTO : questionDTO.getAnswers()){
            Answer answer = AnswerMapper.convertToEntity(answerDTO);
            question.addAnswer(answer);
        }

        return question;
    }

}
