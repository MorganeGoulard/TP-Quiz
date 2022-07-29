package com.example.quiz.api;

import com.example.quiz.api.dto.AnswerMapper;
import com.example.quiz.api.dto.QuestionDTO;
import com.example.quiz.api.dto.QuestionMapper;
import com.example.quiz.business.Answer;
import com.example.quiz.business.Player;
import com.example.quiz.business.Question;
import com.example.quiz.business.service.QuizService;
import com.example.quiz.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    StoreService storeService;

    @GetMapping("play")
    public ResponseEntity<QuestionDTO> getRandomQuestion(){
        Optional<Question> optionalQuestion = quizService.getRandomQuestion();
        if(optionalQuestion.isPresent()){
            Question question = optionalQuestion.get();
            QuestionDTO questionDTO = QuestionMapper.convertToDTO(question);
            return ResponseEntity.status(HttpStatus.OK).body(questionDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("play/question{idquestion}/answer{idanswer}/player{idplayer}")
    public ResponseEntity<String> validateAnswer(@PathVariable("idquestion") Long idQuestion, @PathVariable("idanswer") Long idAnswer, @PathVariable("idplayer") Long idPlayer){
        boolean questionExist = storeService.questionExist(idQuestion);
        boolean answerInQuestion = storeService.answerInQuestion(idAnswer,idQuestion);
        if(questionExist){
            if(answerInQuestion) {
                if (quizService.validateAnswer(idQuestion, idAnswer, idPlayer)) {
                    return ResponseEntity.status(HttpStatus.OK).body("Bonne réponse");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Mauvaise réponse");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La réponse à la question n'existe pas ");
            }
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La question n'existe pas");
            }
    }

    @GetMapping("overallranking")
    public List<Player> getPlayersOrderByScore(){
       return quizService.getPlayersOrderByScore();
    }



}
