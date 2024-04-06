package com.example.curvasbackmvp.infra.exceptions.user;

import java.util.HashMap;
import java.util.Map;

public class PrivateProfileException extends RuntimeException {
    public PrivateProfileException() {
        super("This profile is currently private");
    }

    public PrivateProfileException(String name) {
        super(name+"'s profile is currently private");
    }
    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
