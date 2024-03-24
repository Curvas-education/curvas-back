package com.example.curvasbackmvp.infra.exceptions.exam;

import java.util.HashMap;
import java.util.Map;

public class LowDurationExamException  extends RuntimeException {
    public LowDurationExamException() {
        super("The exam must last more than 1 hour");
    }
    public LowDurationExamException(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
