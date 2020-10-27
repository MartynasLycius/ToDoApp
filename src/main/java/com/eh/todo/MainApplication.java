package com.eh.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Md. Emran Hossain <emranhos1@gmail.com>
 * @version   1.0.00
 * @since     1.0.00
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        System.out.append("Hello");
    }
}
