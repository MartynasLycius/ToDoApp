/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 5:26 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.service.impl;

import com.salahin.todo.constant.MessageConstant;
import com.salahin.todo.core.ResponseObject;
import com.salahin.todo.entities.TodoEntity;
import com.salahin.todo.model.TodoModel;
import com.salahin.todo.repository.TodoRepository;
import com.salahin.todo.service.ToDoService;
import com.salahin.todo.utilities.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements ToDoService {
	
	private static final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);
	private static final ModelMapper modelMapper = new ModelMapper();
	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@Override
	public ResponseObject createTodo(TodoModel todoModel) {
		TodoEntity todoEntity;
		ResponseObject responseObject;
		try{
			todoEntity = modelMapper.map(todoModel,TodoEntity.class);
			todoEntity = this.todoRepository.save(todoEntity);
			responseObject = UtilityMethods.buildResponseObject(todoEntity,
				MessageConstant.SUCCESSFULLY_TODO_CREATED,
				HttpStatus.OK);
		}catch (Exception ex){
			log.error("createTodo method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_CREATE,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getTodoById(UUID uuid) {
		Optional<TodoEntity> todoEntity;
		TodoModel todoModel;
		ResponseObject responseObject;
		try{
			todoEntity = this.todoRepository.findById(uuid);
			if(todoEntity.isPresent()){
				todoModel = modelMapper.map(todoEntity.get(),TodoModel.class);
				responseObject = UtilityMethods.buildResponseObject(todoModel,
					MessageConstant.SUCCESSFULLY_GET_TODO_BY_PROVIDED_ID,
					HttpStatus.OK);
			}else {
				responseObject = UtilityMethods.buildResponseObject(null,
					MessageConstant.REQUESTED_TODO_DOES_NOT_EXIST_NOW,
					HttpStatus.NO_CONTENT);
			}
		}catch (Exception ex){
			log.error("getTodoById method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_UPDATE_TODO,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getAllTodo() {
		List<TodoModel> todoModelList;
		ResponseObject responseObject;
		try{
			todoModelList =  this.todoRepository.findAllToDo()
				.stream()
				.map(element -> modelMapper.map(element, TodoModel.class))
				.collect(Collectors.toList());
			responseObject = UtilityMethods.buildResponseObject(todoModelList,
				MessageConstant.SUCCESSFULLY_GET_ALL_TODO,
				HttpStatus.OK);
			
		}catch (Exception ex){
			log.error("getAllTodo method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_GET_ALL_TODO,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseObject;
	}
	
	@Override
	public ResponseObject deleteTodoById(UUID uuid) {
		ResponseObject responseObject;
		try{
			this.todoRepository.deleteTodoById(uuid);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.SUCCESSFULLY_DELETE_TODO,
				HttpStatus.OK);
		}catch (Exception ex){
			log.error("deleteTodoById method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_DELETE_TODO,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
}
