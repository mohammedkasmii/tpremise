package com.ehei.gi4.payement.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RemiseException.class)
    public ResponseEntity<String> handleRemiseException(RemiseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}