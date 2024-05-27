package com.example.curvasbackmvp.controllers;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.group.GroupRequestDTO;
import com.example.curvasbackmvp.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_STUDENT')") // Permissão apenas para admin ou gestor !!
    public ResponseEntity<List<Group>> findGroups() {
        return ResponseEntity.ok().body(groupService.findUserGroups());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_STUDENT')")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequestDTO group) {
        return ResponseEntity.ok().body(groupService.createGroup(group));
    }

}
