package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.infra.exceptions.user.UserNotFoundException;
import com.example.curvasbackmvp.infra.security.LoginResponseDTO;
import com.example.curvasbackmvp.infra.security.TokenService;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.AuthenticationDTO;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.services.StudentService;
import com.example.curvasbackmvp.services.TeacherService;
import com.example.curvasbackmvp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) throws BadCredentialsException {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(""); // TODO: criar um exception para o bad credential;
        }
    }

    @PostMapping("/student/register")
    public ResponseEntity<String> register(@RequestBody Student student) throws EmailAlreadyExistsException {
        if(userService.findUserEmail(student.getEmail()) != null) throw new EmailAlreadyExistsException();
        studentService.create(student);
        return ResponseEntity.ok().body("Student created successfully");
    }

    @PostMapping("/teacher/register")
    public ResponseEntity<String> register(@RequestBody Teacher teacher) throws EmailAlreadyExistsException {
        if(userService.findUserEmail(teacher.getEmail()) != null) throw new EmailAlreadyExistsException();
        teacherService.create(teacher);
        return ResponseEntity.ok().body("Teacher created successfully");
    }


}
