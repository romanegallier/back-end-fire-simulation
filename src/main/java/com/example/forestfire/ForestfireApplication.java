package com.example.forestfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ForestfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestfireApplication.class, args);
    }

}
