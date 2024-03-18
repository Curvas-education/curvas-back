package com.example.curvasbackmvp.infra.exceptions;

import com.example.curvasbackmvp.infra.exceptions.question.QuestionNotFoundException;
import com.example.curvasbackmvp.infra.exceptions.question.UnauthorizedDeleteException;
import com.example.curvasbackmvp.infra.exceptions.user.EmailAlreadyExistsException;
import com.example.curvasbackmvp.infra.exceptions.user.RegistrationAlreadyExistsException;
import com.example.curvasbackmvp.infra.exceptions.user.UserNotFoundException;
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

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Object> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 404);
        errorMap.put("message", "Question with this ID doesn't exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

    @ExceptionHandler(UnauthorizedDeleteException.class)
    public ResponseEntity<Object> handleUnauthorizedDeleteException(UnauthorizedDeleteException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 401);
        errorMap.put("message", "You can't delete other person's question");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }


}