package com.murad.todoapp.service;


import com.murad.todoapp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
public interface UserService {

    User save(User user);
    Page<User> findAll(Pageable pageable);
    List<User> findAll();
    int countByEmail(String email);
    User findOneById(Integer integer);
    User findByEmail(String email);
    User editAndSave(User editUser);
    Page<User> search(String arg, Pageable pageable);
    void deleteById(Integer Id);
}
