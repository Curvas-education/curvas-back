package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getLoggedUserSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (User) userRepository.findByEmail(userDetails.getUsername());
    }
}
