package com.murad.todoapp.serviceImpl;


import com.murad.todoapp.config.AuthenticatedUser;
import com.murad.todoapp.domain.User;
import com.murad.todoapp.repository.UserRepository;
import com.murad.todoapp.utility.Maneger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Service
public class AuthenticatedUserImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if (Maneger.checkExpiration()) {
            throw new UsernameNotFoundException("License Expired");
        }

        User user = userRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("The user " + s + " does not exist");
        }

        if (user.isActive()) {
            return new AuthenticatedUser(user);
        } else {
            return new AuthenticatedUser(user.getPasswordHash(), user.getEmail());
        }


    }
}
