package com.example.quiz.api;

import com.example.quiz.business.Answer;
import com.example.quiz.business.Player;
import com.example.quiz.business.Question;
import com.example.quiz.business.service.QuizService;
import com.example.quiz.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("store/question")
    public void addQuestion(@RequestBody Question question){
        storeService.addQuestion(question);
    }

    @PostMapping("store/player")
    public void addPlayer(@RequestBody Player player){
        storeService.addPlayer(player);
    }

    @DeleteMapping("store/player/{id}")
    public void deletePlayer(@PathParam("id") Long id){
        storeService.deletePlayer(id);
    }


}
