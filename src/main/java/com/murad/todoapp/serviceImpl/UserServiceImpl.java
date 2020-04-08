package com.murad.todoapp.serviceImpl;


import com.murad.todoapp.domain.User;
import com.murad.todoapp.repository.UserRepository;
import com.murad.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public int countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    @Override
    public User findOneById(Integer integer) {
        return userRepository.findOneById(integer);
    }

    @Override
    public User editAndSave(User editUser) {
        return userRepository.save(editUser);
    }

    @Override
    public Page<User> search(String arg, Pageable pageable) {
        return userRepository.findByEmailContainsOrRoleContains(
                arg,arg,pageable
        );
    }

    @Override
    public void deleteById(Integer Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
