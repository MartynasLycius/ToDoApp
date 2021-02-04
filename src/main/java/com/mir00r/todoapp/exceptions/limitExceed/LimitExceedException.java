package com.mir00r.todoapp.exceptions.limitExceed;


import com.mir00r.todoapp.exceptions.invalid.InvalidException;

public class LimitExceedException extends InvalidException {
    public LimitExceedException() {
    }

    public LimitExceedException(String message) {
        super(message);
    }

}
