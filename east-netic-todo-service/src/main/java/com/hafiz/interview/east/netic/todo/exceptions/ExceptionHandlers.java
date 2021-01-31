package com.hafiz.interview.east.netic.todo.exceptions;

import com.hafiz.interview.east.netic.todo.dataclass.common.ErrorMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExceptionHolders.GeneralServerException.class)
    public ErrorMessageDTO handleGeneralServerException(final ExceptionHolders.GeneralServerException ex) {
        return new ErrorMessageDTO(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(ExceptionHolders.InvalidRequestException.class)
    public ErrorMessageDTO handleInvalidRequestException(final ExceptionHolders.InvalidRequestException ex) {
        return new ErrorMessageDTO(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExceptionHolders.ResourceNotFoundException.class)
    public ErrorMessageDTO handleResourceNotFoundException(final ExceptionHolders.ResourceNotFoundException ex) {
        return new ErrorMessageDTO(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessageDTO handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        List<StringBuilder> readableErrors = this.getReadableErrors(ex);
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), readableErrors.toString());
    }

    private List<StringBuilder> getReadableErrors(MethodArgumentNotValidException ex) {
        List<StringBuilder> readableErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = ((FieldError) error);
            StringBuilder readableError = new StringBuilder(error.getDefaultMessage() + " but '" + fieldError.getRejectedValue() + "' value found in " + fieldError.getField() + " field");
            readableErrors.add(readableError);
        });
        return readableErrors;
    }
}