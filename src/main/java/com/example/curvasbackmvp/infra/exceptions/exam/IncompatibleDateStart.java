package com.example.curvasbackmvp.infra.exceptions.exam;

import java.util.HashMap;
import java.util.Map;

public class IncompatibleDateStart extends RuntimeException {
    public IncompatibleDateStart() {
        super("Incompatible exam start data");
    }
    public IncompatibleDateStart(String message) {
        super(message);
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 400);
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
