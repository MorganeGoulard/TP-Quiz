package com.example.quiz.view;

import com.example.quiz.api.dto.QuestionDTO;
import com.example.quiz.api.dto.QuestionMapper;
import com.example.quiz.business.Answer;
import com.example.quiz.business.Player;
import com.example.quiz.business.Question;
import com.example.quiz.business.service.QuizService;
import com.example.quiz.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
public class QuizGameController {

    @Autowired
    QuizService quizService;

    @Autowired
    StoreService storeService;

    @GetMapping("/playgame")
    public String getRandomQuestion(Model model){
        QuestionDTO questionDTO = QuestionMapper.convertToDTO(quizService.getRandomQuestion().get());
        model.addAttribute("question", questionDTO);
        model.addAttribute("answers", questionDTO.getAnswers());
        model.addAttribute("players", storeService.getAllPlayers());
        return "quiz";
    }

    @PostMapping("/playgame")
    public RedirectView playGame(@RequestParam Question question, Answer answer, Player player, Model model){
        boolean response = quizService.validateAnswer(question.getId(), answer.getId(), player.getId());
        model.addAttribute("response", response);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        model.addAttribute("player", player);
        return new RedirectView("result.html");
    }

   @PostMapping("/result")
    public String getResult(@RequestParam Long idQuestion, @RequestParam Long idAnswer, @RequestParam Long idPlayer, Model model){
       boolean response = quizService.validateAnswer(idQuestion, idAnswer, idPlayer);
       model.addAttribute("response", response);
       return "result";
   }

   @PostMapping("/newquestion")
    public String addQuestion(Question newQuestion, Answer answer, Model model){
        storeService.addQuestion(newQuestion);
        model.addAttribute("question", newQuestion);
        model.addAttribute("answer", answer);
        return "newquestion";
   }

   @GetMapping("/newquestion")
    public String getAllQuestions(Model model){
        model.addAttribute("questions", storeService.getAllQuestion());
        return "newquestion";
   }

}
