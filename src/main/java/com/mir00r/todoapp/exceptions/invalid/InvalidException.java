package com.mir00r.todoapp.exceptions.invalid;

public class InvalidException extends Throwable{
    public InvalidException() {
    }

    public InvalidException(String message) {
        super(message);
    }
}
