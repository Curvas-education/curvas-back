package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.infra.exceptions.question.QuestionNotFoundException;
import com.example.curvasbackmvp.infra.exceptions.question.UnauthorizedDeleteException;
import com.example.curvasbackmvp.models.question.Alternative;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.AlternativeRepository;
import com.example.curvasbackmvp.repositories.QuestionRepository;
import com.example.curvasbackmvp.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(String id, User user) throws RuntimeException {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isEmpty()) {
            throw new QuestionNotFoundException();
        }

        if(!Objects.equals(question.get().getAuthor().getEmail(), user.getEmail())) {
            throw new UnauthorizedDeleteException();
        }
        questionRepository.deleteById(id);
    }

    public Question updateQuestion(Question question) {
        Question question1 = questionRepository.findById(question.getId()).get();
        question.getAlternatives().forEach(alternative -> {
            if(alternative.getId() != null) {
                alternative.setQuestion(question1);
                alternativeRepository.save(alternative);
            } else {
                Alternative alternative1;
                alternative1 = alternative;
                alternative1.setQuestion(question1);
                alternativeRepository.save(alternative1);
            }
        });

        List<String> alternativeList = question.getAlternatives().stream().map(Alternative::getId).toList();
        question1.getAlternatives().forEach(alternative -> {
            if(!alternativeList.contains(alternative.getId())) {
                System.out.println("delete " + alternative.getId());
                alternative.setQuestion(null);
                alternativeRepository.save(alternative);
                alternativeRepository.deleteById(alternative.getId());
            }
        });

        question1.setDescription(question.getDescription());
        question1.setTip(question.getTip());
        question1.setDifficulty(question.getDifficulty());
        question1.setSource(question.getSource());
        question1.setSourceImage(question.getSourceImage());
        question1.setAltImage(question.getAltImage());

        questionRepository.save(question1);

        return question;
    }

    public Page<Question> findQuestionByDescription(String description, Pageable pageable) {
        return questionRepository.findAllByDescriptionContaining(description, pageable);
    }
}
