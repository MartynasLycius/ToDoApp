package com.mir00r.todoapp.exceptions.unknown;


import com.mir00r.todoapp.exceptions.forbidden.ForbiddenException;

public class WtfException extends ForbiddenException {
    public WtfException() {
    }

    public WtfException(String message) {
        super(message);
    }
}
