package com.vaadin.todo.execption;

public class internalServerErrorException extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    private String code = "500";


    public internalServerErrorException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public internalServerErrorException(String message) {
        super(message);
    }

    public internalServerErrorException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
