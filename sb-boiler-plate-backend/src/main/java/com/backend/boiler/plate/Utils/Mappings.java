package com.backend.boiler.plate.Utils;

public class Mappings {
	public static final String API = "/api";
	public static final String LOGIN = "/public/login/check";
	
	//User Mappings
	public static final String API_USERS = "/users";
	public static final String API_USER_INFO = API_USERS+"/info";
	public static final String API_USER_ADD = API_USERS;
	public static final String API_USER_SINGLE = API_USERS+"/{id}";
	
	//Todos Mappings
	public static final String API_TODOS = "/todos";
	public static final String API_TODOS_ADD = API_TODOS;
	public static final String API_TODOS_SINGLE = API_TODOS+"/{id}";
	
	
}
