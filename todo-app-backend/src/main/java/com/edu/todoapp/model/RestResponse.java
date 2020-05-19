package com.edu.todoapp.model;

public class RestResponse {
    private Boolean status;
    private String message;

    public RestResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
