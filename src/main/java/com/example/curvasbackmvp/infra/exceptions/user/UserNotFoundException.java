package com.example.curvasbackmvp.infra.exceptions.user;

import java.util.HashMap;
import java.util.Map;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Incorrect credentials");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }


}
