package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.exceptions.exam.IncompatibleDateStart;
import com.example.curvasbackmvp.infra.exceptions.exam.LowDurationExamException;
import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.models.exam.*;
import com.example.curvasbackmvp.repositories.ExamRepository;
import com.example.curvasbackmvp.services.AnswerService;
import com.example.curvasbackmvp.services.ExamService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    ExamService examService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepository examRepository;
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<Exam> create(@RequestBody ExamRequestDTO examData) throws Exception {
        if(!examData.getStartDate().isBefore(examData.getEndDate())) throw new IncompatibleDateStart();
        if(examData.getStartDate().plusHours(1).isAfter(examData.getEndDate())) throw new LowDurationExamException();

        Exam exam = examService.createExam(examData);
        return ResponseEntity.ok().body(exam);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public ResponseEntity<ExamDetails> findExam(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(examService.getExamDetails(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public ResponseEntity<List<ExamDTO>> allExams(@RequestParam(defaultValue = "pending") String status) {
        return ResponseEntity.ok(examService.findExams(status));
    }

}
