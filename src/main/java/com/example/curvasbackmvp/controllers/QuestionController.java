package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/teacher")
    public ResponseEntity<Page<Question>> getQuestions(Authentication authentication, @PageableDefault(size=10, sort="id") Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(questionService.getQuestions(user, pageable));
    }

    @GetMapping()
    public ResponseEntity<Page<Question>> findQuestions(@PageableDefault(size=10, sort="id") Pageable pageable) {
        return ResponseEntity.ok().body(questionService.findAllQuestions(pageable));
    }

    @PostMapping()
    public ResponseEntity<Question> createQuestion(@RequestBody Question question, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(questionService.saveQuestion(question, user));
    }

    @PutMapping()
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable String id) {
        Question question = questionService.findQuestion(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping( "/search/by-description/{description}")
    public ResponseEntity<Page<Question>> getQuestionByDescription(@PathVariable(required = false) String description, @PageableDefault(size=10, sort="id") Pageable pageable) {
        if(description.isEmpty() || description == null) {
            Page<Question> questions = questionService.findQuestionByDescription(description, pageable);
            return ResponseEntity.ok(questions);
        } else  {
            Page<Question> questions = questionService.findAllQuestions(pageable);
            return ResponseEntity.ok(questions);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id, Authentication authentication) throws Exception {
        // TODO: utilizar preautorize
        User user = (User) authentication.getPrincipal(); // TODO: professor só pode deletar ou atualizar suas proprias questões
        questionService.deleteQuestion(id, user);
        return ResponseEntity.ok().body("Question deleted");
    }

}
