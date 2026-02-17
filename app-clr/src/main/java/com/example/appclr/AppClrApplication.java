package com.example.appclr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication
public class AppClrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppClrApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * run after Sprig has started
     */
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        return args -> {
            ResponseEntity<List<Room>> rooms =
                    restTemplate.exchange("http://localhost:8080/api/rooms", HttpMethod.GET, null,
                            new ParameterizedTypeReference<>() {
                            });
            rooms.getBody().stream().map(Room::getId).forEach(System.out::println);
        };
    }
}


