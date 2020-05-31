package com.todo.user.service;

import com.todo.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Stream;

public interface UserSerive {
    void registerUser(User user);
    void deleteUser(User user);
    User loadUserByUserName(String username);
    UserDetails loadUserByUsername(String username);
    List<User> findAll();
    Stream<User> findAllAsStream();
    boolean isUniqueEmail(String email, String oldEmail);
}
