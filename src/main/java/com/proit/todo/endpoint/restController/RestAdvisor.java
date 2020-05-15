package com.proit.todo.endpoint.restController;

import com.proit.todo.core.exceptions.RecordNotFoundException;
import com.proit.todo.core.exceptions.UnprocessedEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { RecordNotFoundException.class })
    protected ResponseEntity<Object> recordNotFoundExceptionHandler(RecordNotFoundException ex) {

        Map<String,String> body = new HashMap<>();
        body.put("msg",ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(body);
    }

    @ExceptionHandler(value
            = { UnprocessedEntityException.class })
    protected ResponseEntity<Object> unprocessedEntityExceptionHandler(UnprocessedEntityException ex) {


        List<ObjectError> objectErrorList = ex.getErrors()!=null?
                                                    ex.getErrors().getAllErrors()
                                                    :new ArrayList<>(0);

        Map<String,String> body =  objectErrorList
                                        .stream()
                                        .collect(
                                                Collectors
                                                        .toMap(
                                                                ObjectError::getObjectName,
                                                                ObjectError::getDefaultMessage
                                                        )
                                        );
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(body);
    }




}
