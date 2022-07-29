package com.example.quiz.business.service;

import com.example.quiz.business.Answer;
import com.example.quiz.business.Player;
import com.example.quiz.business.Question;
import com.example.quiz.dao.AnswerRepository;
import com.example.quiz.dao.PlayerRepository;
import com.example.quiz.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    PlayerRepository playerRepository;


    public void addQuestion (Question question){
        for (Answer answer : question.getAnswers() ){
            answer.setQuestion(question);
        }
        questionRepository.save(question);
    }

    public boolean questionExist(Long idQuestion){
        return questionRepository.existsById(idQuestion);
    }

    public boolean answerInQuestion(Long idAnswer, Long idQuestion){
        Optional<Answer> optionalAnswer = answerRepository.findById(idAnswer);
        if(optionalAnswer.isPresent()){
            Answer answer = optionalAnswer.get();
            if(idQuestion.equals(answer.getQuestion().getId())){
                return true;
            }
        }
        return false;
    }

    public void addPlayer(Player player){

        playerRepository.save(player);
    }

    public Player getPlayerById(Long idPlayer){
        Player player = playerRepository.findById(idPlayer).get();
        return player;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public void deletePlayer(Long idPlayer){
        playerRepository.deleteById(idPlayer);
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }


}
