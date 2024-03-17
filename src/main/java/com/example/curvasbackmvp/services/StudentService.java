package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void create(Student student) throws RegistrationAlreadyExistsException {

        if(studentRepository.findById(student.getRegistration()).isPresent()) throw new RegistrationAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(encryptedPassword);

        studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

}
