package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
