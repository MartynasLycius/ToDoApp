package com.proit.todoapp.request;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RequestHandler<T> {
	public ResponseEntity<T> createRequest(String url, T entity, HttpMethod method);
	public ResponseEntity<T> createRequest(String url, T entity, HttpMethod method,Map<String,String> parameters);
}
