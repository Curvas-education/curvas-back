package com.example.curvasbackmvp.models.user;

import jakarta.validation.constraints.AssertFalse;

public record ViewResponseDTO(
        Boolean isPrivate,
        String name,
        String icon,
        UserRole userRole,
        String about
) {
    public ViewResponseDTO(User user) {
        this(false, user.getName(), user.getIcon(), user.getUserRole(), user.getAbout());
    }
}
