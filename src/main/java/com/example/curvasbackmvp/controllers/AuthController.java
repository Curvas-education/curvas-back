package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.security.LoginResponseDTO;
import com.example.curvasbackmvp.infra.security.TokenService;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.user.AuthenticationDTO;
import com.example.curvasbackmvp.models.user.RegisterDTO;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.StudentRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.example.curvasbackmvp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Student student) {
        System.out.println("teste01");
        if(userRepository.findByEmail(student.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(encryptedPassword);
        studentService.create(student);

        return ResponseEntity.ok().build();
    }
}
