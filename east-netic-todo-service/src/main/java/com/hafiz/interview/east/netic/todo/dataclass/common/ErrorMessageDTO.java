package com.hafiz.interview.east.netic.todo.dataclass.common;

import lombok.Data;

@Data
public class ErrorMessageDTO {
    private int code;
    private String message;

    public ErrorMessageDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
