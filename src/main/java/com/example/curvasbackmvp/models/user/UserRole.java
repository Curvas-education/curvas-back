package com.example.curvasbackmvp.models.user;

import lombok.Getter;

@Getter
public enum UserRole {
    TEACHER("teacher"),
    STUDENT("student"),
    MANAGER("manager"),
    ADMIN("admin"); // criado apenas para desenvolvimento

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
