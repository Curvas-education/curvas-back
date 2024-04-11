package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.UserRole;
import com.example.curvasbackmvp.repositories.TeacherRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    public void create(Teacher teacher) throws RegistrationAlreadyExistsException {
        if(teacherRepository.findById(teacher.getRegistration()).isPresent()) throw new RegistrationAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(teacher.getPassword());
        teacher.setPassword(encryptedPassword);
        teacher.setSlug(userService.createSlug(teacher.getName()));
        teacher.setUserRole(UserRole.TEACHER);
        teacherRepository.save(teacher);
    }
}
