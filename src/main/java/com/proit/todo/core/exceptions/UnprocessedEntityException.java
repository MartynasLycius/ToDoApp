package com.proit.todo.core.exceptions;

import org.springframework.validation.Errors;

public class UnprocessedEntityException extends RuntimeException{
    private Errors errors;
    public UnprocessedEntityException(Errors errors) {
        super();
        this.errors = errors;

    }

    public Errors getErrors() {
        return errors;
    }
}
