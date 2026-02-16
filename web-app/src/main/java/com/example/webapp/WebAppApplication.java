package com.example.webapp;

import com.example.webapp.data.repository.RoomRepository;
import com.example.webapp.data.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoomRepository roomRepository, StaffRepository staffRepository) {
        return args -> {
            roomRepository.findAll().forEach(System.out::println);
            staffRepository.findAll().forEach(System.out::println);
        };
    }
}
