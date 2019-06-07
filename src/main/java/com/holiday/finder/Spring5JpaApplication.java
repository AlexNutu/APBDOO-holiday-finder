package com.holiday.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Spring5JpaApplication {


    public static void main(String[] args) {

        SpringApplication.run(Spring5JpaApplication.class, args);
    }
}
