package com.murad.todoapp.config;


import com.murad.todoapp.domain.User;
import com.murad.todoapp.utility.RoleTodo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */

public class AuthenticatedUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    public AuthenticatedUser(User user) {
        this.username = user.getEmail();
        this.password = user.getPasswordHash();
        this.authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }

    public AuthenticatedUser(String password, String username) {
        this.password = password;
        this.username = username;
        this.authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(RoleTodo.ROLE_BLOCKED.name())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}