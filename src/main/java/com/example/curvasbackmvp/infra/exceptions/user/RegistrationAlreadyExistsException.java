package com.example.curvasbackmvp.infra.exceptions.user;

import java.util.HashMap;
import java.util.Map;

public class RegistrationAlreadyExistsException extends RuntimeException {
    public RegistrationAlreadyExistsException() {
        super("A user with this registration is already created");
    }
    public RegistrationAlreadyExistsException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
