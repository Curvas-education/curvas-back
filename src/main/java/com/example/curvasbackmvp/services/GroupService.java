package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.group.GroupRequestDTO;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.models.user.UserRole;
import com.example.curvasbackmvp.repositories.GroupRepository;
import com.example.curvasbackmvp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

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

    public List<Group> findUserGroups() {
        User user = userService.getLoggedUserSession();
        if (user.getUserRole() == UserRole.TEACHER) {
            return groupRepository.findAllByCreator(user);
        } if (user.getUserRole() == UserRole.STUDENT) {
            List<String> ids = user.getGroups().stream().map(Group::getId).toList();
            return groupRepository.findAllById(ids);
        } else {
            return null;
        }

    }

    public void deleteGroup(String id) {
        User user = userService.getLoggedUserSession();
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            System.out.println("No group found with id " + id);
            return;
            // TODO: retornar um exception para o erro acima
        }

        if (group.get().getCreator() == user) {
            groupRepository.deleteById(id);
        } else {
            System.out.println("You CANT delete this group");

            // TODO: retornar um exception para o erro acima
        }
    }

    public void editGroup(Group group) {
    }
}
