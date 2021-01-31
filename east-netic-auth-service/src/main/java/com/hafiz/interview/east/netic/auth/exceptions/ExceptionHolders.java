package com.hafiz.interview.east.netic.auth.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ExceptionHolders {

    @Getter
    @RequiredArgsConstructor
    public static class ServiceException extends RuntimeException {
        private final int code;
        private final String message;
    }

    public static class ResourceNotFoundException extends ServiceException {
        public ResourceNotFoundException(String message) {
            super(2000, message);
        }
    }

    public static class InvalidRequestException extends ServiceException {
        public InvalidRequestException(String message) {
            super(2001, message);
        }
    }

}
