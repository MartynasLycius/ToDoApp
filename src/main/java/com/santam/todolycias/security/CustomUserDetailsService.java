package com.santam.todolycias.security;

import java.util.Collection;

import com.santam.todolycias.backend.entity.Users;
import com.santam.todolycias.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Sanower Tamjit
 *
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(userName)
       .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
         getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        String[] userRoles = user.getUserRolesById().stream().map((role) -> role.getRolesByRoleId().getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}