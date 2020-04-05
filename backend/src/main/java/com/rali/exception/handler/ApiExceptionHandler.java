package com.rali.exception.handler;


import com.rali.constant.PropertyNamespace;
import com.rali.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity handleApiException(Exception ex, WebRequest request) {

        String errorMessage = ((ApiException) ex).getErrorMessage();
        HttpStatus httpStatus = ((ApiException) ex).getHttpStatus();


        JSONObject response = new JSONObject();
        response.put(PropertyNamespace.STATUS, httpStatus.value());
        response.put(PropertyNamespace.MESSAGE, errorMessage);

        log.error("generic exception handler initiated");
        log.error("error" + response.toJSONString());

        return new ResponseEntity(response, httpStatus);
    }
}
