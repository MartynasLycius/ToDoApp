package com.eastnetic.client.ClientApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/login", "/login/**", "/error/**", "/accessDenied/**", "/vaadinServlet/**").permitAll()
                .antMatchers("/authorized", "/**").fullyAuthenticated();
        // @formatter:on
    }

}
