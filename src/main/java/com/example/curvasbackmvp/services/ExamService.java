package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.exam.*;
import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    private ExamDTOMapper examDTOMapper;
    @Autowired
    private ModelMapper modelMapper;


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

    public Exam findExamById(String id) {
        return examRepository.findById(id).get();
    }

    public ExamDetails getExamDetails(String id) {
        Exam exam = examRepository.findById(id).get();
        // TODO : preparar exception caso não encontre exam com o id designado
        return new ExamDetails(exam);
    }
    public List<ExamDTO> findAll() {
        return examRepository
                .findAll()
                .stream()
                .map(examDTOMapper).toList();
    }

    public List<ExamDTO> findExams(String status) {
        User user = userService.getLoggedUserSession();
        List<String> exams_id = studentRepository.findStudentExams(user.getRegistration());
        List<String> exams_answered = answerRepository.findAll().stream().map(answer -> answer.getExam().getId()).toList();
        List<Exam> exams = examRepository.findAllById(exams_id);
        List<Exam> examsList;
        switch(status) {
            case "finished":
                examsList = exams.stream().filter(examItem -> exams_answered.contains(examItem.getId())).toList();
            case "pending":
                examsList = exams.stream().filter(examItem -> !exams_answered.contains(examItem.getId())).toList();
            case "progress":
                examsList = null;
            default:
                examsList = exams.stream().filter(examItem -> exams_answered.contains(examItem.getId())).toList();
        }
        return examsList.stream().map(examDTOMapper).toList();
    }



}


