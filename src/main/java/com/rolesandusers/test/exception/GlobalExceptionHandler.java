package com.rolesandusers.test.exception;

import com.rolesandusers.test.web.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Map<String, String>>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new GenericResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleAlreadyExistException.class)
    public ResponseEntity<GenericResponse> handleRoleAlreadyExist (RoleAlreadyExistException ex){
        return new ResponseEntity<>(
                new GenericResponse(1001, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<GenericResponse> handleRoleAlreadyExist (RoleNotFoundException ex){
        return new ResponseEntity<>(
                new GenericResponse(1004, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<GenericResponse> handleRoleAlreadyExist (UserAlreadyExistException ex){
        return new ResponseEntity<>(
                new GenericResponse(1001, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericResponse> handleRoleAlreadyExist (UserNotFoundException ex){
        return new ResponseEntity<>(
                new GenericResponse(1004, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TargetRoleHasUsersException.class)
    public ResponseEntity<GenericResponse> handleRoleAlreadyExist (TargetRoleHasUsersException ex){
        return new ResponseEntity<>(
                new GenericResponse(1003, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
