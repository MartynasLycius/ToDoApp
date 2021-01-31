package com.hafiz.interview.east.netic.auth.core.config.jwttoken;

import com.hafiz.interview.east.netic.auth.entities.User;
import com.hafiz.interview.east.netic.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SpringSecUserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}
