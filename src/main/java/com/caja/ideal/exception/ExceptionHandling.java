package com.caja.ideal.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach( err ->  {
                    errors.put("Campo no valido", err.getField());
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Object> userNotFoundException(UserNotFoundException e){
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getMessage(), "Not found user");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("Message ", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFound.class)
    ResponseEntity<Object> resourceNotFound(ResourceNotFound e){
        Map<String, String> errors = new HashMap<>();
        errors.put("Message ",e.getMessage() );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Message", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }



}
