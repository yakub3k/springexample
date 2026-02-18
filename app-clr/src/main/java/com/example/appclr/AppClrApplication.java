package com.example.appclr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;


@SpringBootApplication
public class AppClrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppClrApplication.class, args);
    }

    private static final String QUEUE_NAME = "room-cleaner";
    private static final String EXCHANGE_NAME = "operations";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("landon.#");
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
                    restTemplate.exchange("http://localhost:8080/api/rooms", HttpMethod.GET,
                            createCredentials("user", "password"),
                            new ParameterizedTypeReference<>() {
                            });
            rooms.getBody().stream().map(r -> new AsyncPayload(r.getId(), "ROOM"))
                    .forEach(payload -> {
                        try {
                            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "landon.rooms.cleaner",
                                    objectMapper.writeValueAsString(payload));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });
        };
    }

    private HttpEntity<String> createCredentials(String user, String password) {
        String plain = user + ":" + password;
        byte[] plainCredsBytes = plain.getBytes();
        byte[] base64Bytes = Base64.getUrlEncoder().encode(plainCredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(base64Bytes));
        return new HttpEntity<String>(headers);
    }
}


