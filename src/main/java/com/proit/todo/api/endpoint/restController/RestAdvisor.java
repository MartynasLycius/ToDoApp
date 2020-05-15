package com.proit.todo.api.endpoint.restController;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.proit.todo.core.exceptions.UnprocessedEntityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.proit.todo.core.exceptions.RecordNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
