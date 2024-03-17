package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.question.Alternative;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.AlternativeRepository;
import com.example.curvasbackmvp.repositories.QuestionRepository;
import com.example.curvasbackmvp.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AlternativeRepository alternativeRepository;

    public Question saveQuestion(Question question, User user) {
        List<Alternative> alternativeList = question.getAlternatives();
        if(alternativeList != null) {
            alternativeRepository.saveAll(alternativeList);
            alternativeList.forEach(alternative -> {
                alternative.setQuestion(question);
            });
            question.setAlternatives(alternativeList);
        }
        question.setAuthor((Teacher) user); // TODO : criar questions DTO para evitar mostrar dados sensiveis, tanto do autor quanto da propria
        return questionRepository.save(question);
    }

    public Question findQuestion(String id) {
        return questionRepository.findById(id).orElseThrow();
    }

    public List<Question> getQuestions(User user) {
        return questionRepository.findAllByAuthor_Registration(user.getRegistration());
    }
}
