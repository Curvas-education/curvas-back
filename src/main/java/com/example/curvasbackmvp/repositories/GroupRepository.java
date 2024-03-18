package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByCreator(User user);
}
