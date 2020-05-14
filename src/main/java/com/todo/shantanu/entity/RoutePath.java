package com.todo.shantanu.entity;

import lombok.Getter;

@Getter
public enum RoutePath {
    ROOT(""),
    TODO_VIEW("todoView"),
    EDIT_TODO("edit");

    private final String routingPath;

    RoutePath(String routingPath) {
        this.routingPath = routingPath;
    }
}
