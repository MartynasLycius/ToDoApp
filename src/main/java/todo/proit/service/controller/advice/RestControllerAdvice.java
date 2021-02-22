package todo.proit.service.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import todo.proit.common.exception.BadRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    private final static String ERROR_MESSAGE_KEY = "message";

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e){
        List<ObjectError> errors = e.getErrors() != null ? e.getErrors().getAllErrors() : new ArrayList<>();

        String errorJoinedString = errors
                                    .stream()
                                    .map(ObjectError::getDefaultMessage)
                                    .collect(Collectors.joining(", "));

        Map<String, String> body = new HashMap<>();
        body.put(ERROR_MESSAGE_KEY, errorJoinedString);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }
}
