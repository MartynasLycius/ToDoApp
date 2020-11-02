package com.example.demo;

import com.example.demo.enums.RoleName;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoAppBackendApplication {

    @Autowired
    private RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(TodoAppBackendApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            roleRepository.save(new Role(1L, RoleName.ROLE_USER));
        };
    }
}
