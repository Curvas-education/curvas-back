package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.exceptions.exam.IncompatibleDateStart;
import com.example.curvasbackmvp.infra.exceptions.exam.LowDurationExamException;
import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.exam.ExamRequestDTO;
import com.example.curvasbackmvp.services.ExamService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    ExamService examService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<Exam> create(@RequestBody ExamRequestDTO examData) throws Exception {
        if(!examData.getStartDate().isBefore(examData.getEndDate())) throw new IncompatibleDateStart();
        if(examData.getStartDate().plusHours(1).isAfter(examData.getEndDate())) throw new LowDurationExamException();

        Exam exam = examService.createExam(examData);
        return ResponseEntity.ok().body(exam);
    }
}
