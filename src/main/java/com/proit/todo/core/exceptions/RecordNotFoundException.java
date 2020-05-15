package com.proit.todo.core.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException() {

    }

    public RecordNotFoundException(String message) {
        super(message);
    }
    public <T> RecordNotFoundException(Class<T> tClass,String field,Object value) {
        super("Record not found of "+tClass.getSimpleName()+" by "+field+": "+value);

    }
}
