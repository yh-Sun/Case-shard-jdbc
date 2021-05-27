package com.sun.forward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ForwardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForwardApplication.class, args);
    }

}
