package com.example.curvasbackmvp.infra.exceptions.student;

import java.util.HashMap;
import java.util.Map;

public class StudentWithCPFAlreadyExistsException extends RuntimeException {
    public StudentWithCPFAlreadyExistsException() {
        super("An user with this CPF is already created");
    }
    public StudentWithCPFAlreadyExistsException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
