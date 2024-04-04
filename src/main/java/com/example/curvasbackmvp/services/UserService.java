package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    final Slugify slug = Slugify.builder()
            .customReplacements(Map.of("da", "", "de", "", "di", "", "do", "", "dos", ""))
            .build();

    public User findUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getLoggedUserSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (User) userRepository.findByEmail(userDetails.getUsername());
    }

    public String createSlug(String fullname) {
        String slugString = slug.slugify(fullname.toLowerCase());
        int counter = 1;
        List<User> users = userRepository.findUserBySlugStartsWith(slugString);
        Map<String, User> slugsToUserMap = new HashMap<>();
        for (User user: users) {
            slugsToUserMap.put(user.getSlug(), user);
        }
        String nameSlug = null;
        for(int i = counter; i < Integer.MAX_VALUE; i++) {
            nameSlug = slugString + "-" + i;
            if(!slugsToUserMap.containsKey(nameSlug)) {
                break;
            }
        }
        return nameSlug;
    }
}
