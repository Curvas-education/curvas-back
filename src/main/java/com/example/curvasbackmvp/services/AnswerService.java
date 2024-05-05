package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.exam.Answer;
import com.example.curvasbackmvp.models.exam.AnswerDTO;
import com.example.curvasbackmvp.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AlternativeService alternativeService;

    public void saveUserAnswer(AnswerDTO answerDTO) {
        answerDTO.questionsAwnsered().forEach(answer -> {
            Answer answer1 = new Answer();
            answer1.setUser(studentService.findStudentById(answerDTO.userId()));
            answer1.setExam(examService.findExamById(answerDTO.formId()));
            answer1.setQuestion(questionService.findQuestion(answer.id()));
            answer1.setAlternative(alternativeService.findById(answer.answer()));
            answerRepository.save(answer1);
        });
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }
}
