package com.mir00r.todoapp.exceptions.handler;


import com.mir00r.todoapp.exceptions.exists.AlreadyExistsException;
import com.mir00r.todoapp.exceptions.forbidden.ForbiddenException;
import com.mir00r.todoapp.exceptions.invalid.InvalidException;
import com.mir00r.todoapp.exceptions.notfound.ApartmentNotFoundException;
import com.mir00r.todoapp.exceptions.notfound.FirebaseTokenNotFoundException;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import com.mir00r.todoapp.exceptions.notfound.ProfileNotFoundException;
import com.mir00r.todoapp.exceptions.unknown.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import javax.naming.LimitExceededException;

@ControllerAdvice
public class ExHandler {

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity handleApartmentNotFoundException(ApartmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity handleInvalidException(InvalidException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity handleProfileNotFoundException(ProfileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LimitExceededException.class)
    public ResponseEntity handleLimitExceedException(LimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(FirebaseTokenNotFoundException.class)
    public void handleFirebaseTokenNotFoundException(FirebaseTokenNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity handleUnknownException(FirebaseTokenNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleMultipartException(MultipartException ex) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
    }


}
