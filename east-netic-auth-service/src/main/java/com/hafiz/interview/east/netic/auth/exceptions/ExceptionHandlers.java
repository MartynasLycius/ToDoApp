package com.hafiz.interview.east.netic.auth.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExceptionHolders.InvalidRequestException.class)
    public String handleResourceNotFoundException(final ExceptionHolders.InvalidRequestException ex) {
        return ex.getMessage();
    }

}