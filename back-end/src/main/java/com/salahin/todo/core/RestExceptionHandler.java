package com.salahin.todo.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler{
    @ExceptionHandler(Throwable.class)
    ResponseEntity<String> handleUncaughtException(Throwable throwable) {
        log.error("UncaughtException", throwable);
        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body("{ \"message\" : \"Something terribly went wrong!\" }");
    }
    
}
