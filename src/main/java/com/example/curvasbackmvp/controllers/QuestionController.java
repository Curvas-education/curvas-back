package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/teacher")
    public ResponseEntity<List<Question>> getQuestions(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(questionService.getQuestions(user));
    }

    @GetMapping()
    public ResponseEntity<List<Question>> findQuestions() {
        return ResponseEntity.ok().body(questionService.findAllQuestions());
    }

    @PostMapping()
    public ResponseEntity<Question> createQuestion(@RequestBody Question question, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(questionService.saveQuestion(question, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id, Authentication authentication) throws Exception {
        // TODO: utilizar preautorize
        User user = (User) authentication.getPrincipal(); // TODO: professor só pode deletar ou atualizar suas proprias questões
        questionService.deleteQuestion(id, user);
        return ResponseEntity.ok().body("Question deleted");
    }

}
