package com.example.quiz.api.dto;

import com.example.quiz.business.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {

    public static AnswerDTO convertToDTO(Answer answer){

        AnswerDTO answersDTO = new AnswerDTO();

        answersDTO.setId(answer.getId());
        answersDTO.setProposition(answer.getProposition());

        return answersDTO;
    }

    public static Answer convertToEntity(AnswerDTO answerDTO){

        Answer answer = new Answer();

        answer.setId(answerDTO.getId());
        answer.setProposition(answerDTO.getProposition());

        return answer;
    }


}
