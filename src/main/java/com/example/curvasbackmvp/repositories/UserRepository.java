package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
    User findUserByEmail(String email);
    User findUserBySlug(String slug);

    List<User> findUserBySlugStartsWith(String slugInitials);
}
