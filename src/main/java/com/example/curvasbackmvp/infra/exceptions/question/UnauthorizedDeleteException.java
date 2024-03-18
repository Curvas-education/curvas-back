package com.example.curvasbackmvp.infra.exceptions.question;

import java.util.HashMap;
import java.util.Map;

public class UnauthorizedDeleteException extends RuntimeException {
    public UnauthorizedDeleteException() {
        super("You can't delete other person's question");
    }
    public UnauthorizedDeleteException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
