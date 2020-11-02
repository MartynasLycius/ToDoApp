package com.seal.todoapp.com.seal.todoapp.controller;

import com.seal.todoapp.com.seal.todoapp.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@RestController
public class Advisor {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse("Entity Not Found!!"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        log.error("", ex);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

}
