package com.eh.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eh.todo.util.PropertyUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@SpringBootApplication
public class MainApplication {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        LOG.info("START UP MASSAGE :: {} {}", PropertyUtil.WELCOME, PropertyUtil.STARTING_MASSAGE);
    }
}
