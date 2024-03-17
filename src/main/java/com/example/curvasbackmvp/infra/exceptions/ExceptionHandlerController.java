package com.example.curvasbackmvp.infra.exceptions;

import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorMap());
    }

    @ExceptionHandler(RegistrationAlreadyExistsException.class)
    public ResponseEntity<Object> handleRegistrationAlreadyExists(RegistrationAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorMap());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 401);
        errorMap.put("message", "Non-existent user or invalid password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }

}