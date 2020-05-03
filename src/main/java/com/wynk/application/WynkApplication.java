package com.wynk.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wynk"})
public class WynkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WynkApplication.class, args);
    }
}
