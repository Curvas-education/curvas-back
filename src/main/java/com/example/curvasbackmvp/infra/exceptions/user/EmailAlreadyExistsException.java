package com.example.curvasbackmvp.infra.exceptions.user;

import java.util.HashMap;
import java.util.Map;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("A user with this email is already registered");
    }
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
