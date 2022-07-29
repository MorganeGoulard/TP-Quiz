package com.example.quiz.business.service;

import com.example.quiz.business.Answer;
import com.example.quiz.business.Player;
import com.example.quiz.business.Question;
import com.example.quiz.dao.AnswerRepository;
import com.example.quiz.dao.PlayerRepository;
import com.example.quiz.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    PlayerRepository playerRepository;

    Random random = new Random();

    public Optional<Question> getRandomQuestion(){
        List<Question> questions = questionRepository.findAll();
        if(questions.size() > 0) {
            return Optional.of(questions.get(random.nextInt(0, questions.size())));
        }
        return Optional.empty();
    }


    public boolean validateAnswer(Long questionId, Long answerId, Long idPlayer){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Optional<Player> optionalPlayer = playerRepository.findById(idPlayer);
        if(optionalAnswer.isPresent()) {
            Answer answer = optionalAnswer.get();
            if(answer.getQuestion().getId().equals(questionId)){
                    boolean win = answer.isCorrectAnswer();
                    if(win){
                        Player winner = optionalPlayer.get();
                        int score = winner.getScore();
                        winner.setScore(score+1);
                        playerRepository.save(winner);
                        return true;
                    }
                }
            }

        return false;

    }

    public List<Player> getPlayersOrderByScore(){
        return playerRepository.findAll(Sort.by("score").descending());
    }



}
