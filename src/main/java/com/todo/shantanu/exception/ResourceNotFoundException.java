package com.todo.shantanu.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String str) {
        super(str);
    }

    public ResourceNotFoundException(String str, Throwable throwable) {
        super(throwable);
    }

    public ResourceNotFoundException(String str, Throwable throwable, boolean b, boolean b1) {
        super(str, throwable, b, b1);
    }
}
