package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.group.GroupRequestDTO;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.GroupRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public Group createGroup(GroupRequestDTO groupData) {
        Group group = new Group(groupData);
        List<User> users = userRepository.findAllById(groupData.getUsers());
        User user = userService.getLoggedUserSession();

        group.setCreator((Teacher) user);
        group.setUsers(new HashSet<>(users));
        // TODO: montar DTO para exibição do group
        return groupRepository.save(group);
    }

    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public List<Group> findUserGroups(User user) {
        return groupRepository.findAllByCreator(user);
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }

    public void editGroup(Group group) {
    }
}
