package com.example.appclr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class AppClrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppClrApplication.class, args);
    }

    /**
     * run after Sprig has started
     */
    @Bean
    public CommandLineRunner run() {
        return args -> {
            for (int i = 1; i < 16; i++) {
                String value = switch (new Point(i % 3 == 0, i % 5 == 0)) {
                    case Point(boolean x, boolean y) when x & y -> "Both";
                    case Point(boolean x, boolean y) when x -> "Left";
                    case Point(boolean x, boolean y) when y -> "Right";
                    default -> "-";
                };
                System.out.println(STR."# \{value}");
            }
        };
    }

    public record Point(boolean a, boolean b) {
    }
}


