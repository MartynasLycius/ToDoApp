package com.proit.todoapp.request;

import java.util.Collections;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandlerImpl<T> implements RequestHandler<T> {
	
	public RequestHandlerImpl() {
	}

	@Override
	public ResponseEntity<T> createRequest(String url, T entity, HttpMethod method) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		headers.set("author", header.getAuthor());
		HttpEntity<T> request = new HttpEntity<>(entity, headers);
		ResponseEntity<T> urlResponse = restTemplate.exchange(url, method, request,new ParameterizedTypeReference<T>(){});
		return urlResponse;
	}

	@Override
	public ResponseEntity<T> createRequest(String url, T entity, HttpMethod method,
			Map<String, String> parameters) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		headers.set("author", header.getAuthor());		
		HttpEntity<T> request = new HttpEntity<>(entity, headers);
		ResponseEntity<T> urlResponse = restTemplate.exchange(url, method, request,new ParameterizedTypeReference<T>(){},parameters);
		return urlResponse;
	}

}
