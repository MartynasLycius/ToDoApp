package com.mir00r.todoapp.Routing;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public class Router {

    // Routing URLs for web controller
    public static final String SEARCH_TODO_ITEMS = "/todos";
    public static final String CREATE_TODO_ITEMS_PAGE = "/todos/create";
    public static final String CREATE_TODO_ITEMS = "/todos";
    public static final String UPDATE_TODO_ITEM_PAGE = "/todos/update/{id}";
    public static final String UPDATE_TODO_ITEM = "/todos/{id}";
    public static final String DELETE_TODO_ITEM = "/todos/{id}/delete";

    // Routing URLs for rest controller
    public static final String GET_TODO_ITEMS = "/v1/todos";
    public static final String CREATE_NEW_TODO_ITEMS = "/v1/todos";
    public static final String FIND_TODO_ITEMS = "/v1/todos/{id}";
    public static final String UPDATE_TODO_ITEMS = "/v1/todos/{id}";
    public static final String DELETE_TODO_ITEMS = "/v1/todos/{id}";
}
