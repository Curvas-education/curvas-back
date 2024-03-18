package com.example.curvasbackmvp.infra.exceptions.question;

import java.util.HashMap;
import java.util.Map;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException() {
        super("Question with this ID doesn't exist");
    }
    public QuestionNotFoundException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
