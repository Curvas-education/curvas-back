package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.example.curvasbackmvp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("exams")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_STUDENT')")
    public ResponseEntity<List<Exam>> getStudentExams() {
        return ResponseEntity.ok(studentService.findStudentExams());
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        return null;
    }
}

