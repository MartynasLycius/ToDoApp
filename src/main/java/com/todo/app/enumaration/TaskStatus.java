package com.todo.app.enumaration;

public enum TaskStatus {
    TO_DO("TO DO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private String text;

    TaskStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
