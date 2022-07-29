package com.example.quiz.view;

import com.example.quiz.business.Player;
import com.example.quiz.business.service.QuizService;
import com.example.quiz.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlayersController {


    @Autowired
    QuizService quizService;

    @Autowired
    StoreService storeService;

    @GetMapping("/rankings")
    public String getPlayersOrderByScore(Model model){
        model.addAttribute("players", quizService.getPlayersOrderByScore());
        return "rankings";
    }

    @PostMapping("/newplayer")
    public String addPlayer(Player newPlayer, Model model){
        storeService.addPlayer(newPlayer);
        model.addAttribute("players", storeService.getAllPlayers());
        return "newplayer";
    }

    @GetMapping("/newplayer")
    public String getAllPlayers(Model model){
        model.addAttribute("players", storeService.getAllPlayers());
        return "newplayer";
    }

}
