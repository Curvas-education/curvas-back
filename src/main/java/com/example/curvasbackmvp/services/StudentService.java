package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import com.example.curvasbackmvp.models.exam.Answer;
import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.models.user.UserRole;
import com.example.curvasbackmvp.repositories.AnswerRepository;
import com.example.curvasbackmvp.repositories.ExamRepository;
import com.example.curvasbackmvp.repositories.StudentRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void create(Student student) throws RegistrationAlreadyExistsException {

        if(studentRepository.findById(student.getRegistration()).isPresent()) throw new RegistrationAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(encryptedPassword);
        student.setSlug(userService.createSlug(student.getName()));
        student.setUserRole(UserRole.STUDENT);
        studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }



    public Student findStudentById(String id) {
        return studentRepository.findById(id).get();
    }
}
