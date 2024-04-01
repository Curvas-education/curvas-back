package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.repositories.TeacherRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.example.curvasbackmvp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherService teacherService;



}
