package com.todo.user.service;

import com.todo.user.entity.User;
import com.todo.user.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserSerive{
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user){
        if (user.isPersisted()){
            userRepository.save(user);
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public User loadUserByUserName(String username){
        logger.debug("loading user by username: "+username);
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Stream<User> findAllAsStream(){
        return userRepository.findAll().stream();
    }

    @Override
    public boolean isUniqueEmail(String email, String oldEmail){
        User user= userRepository.findByEmail(email);
        if (user==null) return true;
        return oldEmail != null && oldEmail.equalsIgnoreCase(user.getEmail());
    }
}
