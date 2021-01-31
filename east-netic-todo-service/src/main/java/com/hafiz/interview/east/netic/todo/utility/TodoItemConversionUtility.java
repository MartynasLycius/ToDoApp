package com.hafiz.interview.east.netic.todo.utility;

import com.hafiz.interview.east.netic.todo.core.crud.ConversionUtility;
import com.hafiz.interview.east.netic.todo.dataclass.common.UserDto;
import com.hafiz.interview.east.netic.todo.dataclass.todo.TodoItemDTO;
import com.hafiz.interview.east.netic.todo.entities.TodoItem;
import com.hafiz.interview.east.netic.todo.feignclient.AuthenticationServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TodoItemConversionUtility extends ConversionUtility<TodoItem, TodoItemDTO> {

    private final AuthenticationServiceClient authClient;

    TodoItemConversionUtility(ModelMapper modelMapper, AuthenticationServiceClient authClient) {
        super(modelMapper, TodoItem.class, TodoItemDTO.class);
        this.authClient = authClient;
    }

    @Override
    public Page<TodoItemDTO> getDtoList(Page<TodoItem> entities) {
        List<TodoItemDTO> todos = getTodoResponseDTOS(entities.getContent());
        Set<UUID> userIds = getUserIds(todos);
        Map<UUID, UserDto> users = getUserDtoMap(userIds);
        setDtos(todos, users);
        return new PageImpl<>(todos);
    }

    @Override
    public Optional<TodoItem> buildEntityForCreate(Optional<TodoItemDTO> s) {
        Optional<TodoItem> entity = super.buildEntityForCreate(s);
        //TODO There have an issue in lombok when I've useed user and userId field
        // then userId of dto is not mapping with entity userId
        entity.ifPresent(value -> value.setUserId(s.get().getUserId()));
        return entity;
    }

    private void setDtos(List<TodoItemDTO> todos, Map<UUID, UserDto> users) {
        this.setUserDto(users, todos);
    }

    private List<TodoItemDTO> getTodoResponseDTOS(List<TodoItem> entities) {
        return entities
                  .stream()
                  .map(Optional::of).map(this::getDto).map(Optional::get)
                  .collect(Collectors.toList());
    }

    private Map<UUID, UserDto> getUserDtoMap(Set<UUID> userIds) {
        return authClient.getUsersByIds(userIds)
                  .stream()
                  .collect(Collectors.toMap(UserDto::getId, user -> user));
    }

    private Set<UUID> getUserIds(List<TodoItemDTO> posts) {
        return posts
                  .stream()
                  .map(TodoItemDTO::getUserId)
                  .collect(Collectors.toSet());
    }

    private void setUserDto(Map<UUID, UserDto> users, List<TodoItemDTO> todoItems){
        todoItems.forEach(post -> {
            UserDto user = users.get(post.getUserId());
            if(user != null) post.setUser(user);
        });
    }
}
