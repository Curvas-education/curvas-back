package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public List<Group> findUserGroups(User user) {
        return groupRepository.findAllByCreator(user);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public void editGroup(Group group) {

    }
}
