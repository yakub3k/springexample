package com.example.springexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringExampleApplication.class, args);
    }


    @Bean
    public CommandLineRunner run() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(STR."Alo \{i}");
            }
        };
    }
}
