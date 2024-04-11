package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.student.Student;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.models.user.UserRole;
import com.example.curvasbackmvp.repositories.ExamRepository;
import com.example.curvasbackmvp.repositories.StudentRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    UserService userService;

    @Autowired
    ExamService examService;

    @Autowired
    ExamRepository examRepository;

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

    public List<Exam> findStudentExams() {
        User user = userService.getLoggedUserSession();
        List<String> exams_id = studentRepository.findStudentExams(user.getRegistration());
        return examRepository.findAllById(exams_id);
    }
}
