package com.vaadin.todo.execption;

public class notFoundException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private String code = "404";


    public notFoundException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public notFoundException(String message) {
        super(message);
    }

    public notFoundException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
