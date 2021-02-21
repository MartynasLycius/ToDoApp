/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 26-Jan-2021
 * Time: 1:14 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
 
}
