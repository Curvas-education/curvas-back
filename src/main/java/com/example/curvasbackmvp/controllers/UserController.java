package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.infra.exceptions.user.PrivateProfileException;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.models.user.UserRole;
import com.example.curvasbackmvp.models.user.ViewResponseDTO;
import com.example.curvasbackmvp.repositories.UserRepository;
import com.example.curvasbackmvp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/{slug}")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_STUDENT')")
    public ResponseEntity<ViewResponseDTO> view(@PathVariable String slug, Authentication authentication) {
        User auth = (User) authentication.getPrincipal();
        User user = userService.findUserBySlug(slug);
        ViewResponseDTO response = new ViewResponseDTO(user);

        if (response.isPrivate() && auth.getUserRole().equals(UserRole.STUDENT)) {
            throw new PrivateProfileException(response.name());
        };

        return ResponseEntity.ok().body(response);
    };

}
