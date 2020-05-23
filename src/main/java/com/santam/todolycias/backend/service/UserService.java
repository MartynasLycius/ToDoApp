package com.santam.todolycias.backend.service;

import com.santam.todolycias.backend.entity.Users;
import com.santam.todolycias.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Find All
    public List<Users> findAll(){
        return userRepository.findAll();
    }
    //Find by meail
    public Optional<Users> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    //Count User total
    public long count(long ownBy){
        return userRepository.count();
    }

    //Delete User
    public void deleteUser(Users user){
        userRepository.delete(user);
    }
    //Add User
    public void addUser(Users user){
       if(user == null){
           LOGGER.log(Level.SEVERE,
                   "User info is null, Please try again by providing valid data");
        return;
       }
       userRepository.save(user);
    }


}
