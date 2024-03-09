package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) {
        if(studentRepository.findById(student.getRegistration()).isPresent()){
            return null;
        }
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

}
