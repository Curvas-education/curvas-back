package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.exam.ExamRequestDTO;
import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.ExamRepository;
import com.example.curvasbackmvp.repositories.GroupRepository;
import com.example.curvasbackmvp.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GroupRepository groupRepository;

    public Exam createExam(ExamRequestDTO examData) {
        Exam exam = new Exam(examData);
        User user = userService.getLoggedUserSession();
        List<Question> questions = questionRepository.findAllById(examData.getQuestions());
        List<Group> groups = groupRepository.findAllById(examData.getGroups());
//        groups.forEach(group -> {
//            group.setExams(exam);
//        });
        exam.setGroups(groups);
        exam.setQuestions(questions);
        exam.setTeacher((Teacher) user);
        return examRepository.save(exam);
    }
}
