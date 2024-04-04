package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.infra.security.LoginResponseDTO;
import com.example.curvasbackmvp.infra.security.TokenService;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.AuthenticationDTO;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.example.curvasbackmvp.services.StudentService;
import com.example.curvasbackmvp.services.TeacherService;
import com.example.curvasbackmvp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:19006")
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
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data, UriComponentsBuilder uriBuilder) throws BadCredentialsException {
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
        System.out.println(student);
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

    @GetMapping("/validate/{token}")
    public ResponseEntity<UserDetails> validate(@PathVariable String token) throws Exception {
        var login = tokenService.validateToken(token);
        UserDetails user = userRepository.findByEmail(login);
        return ResponseEntity.ok().body(user);
    }

}
