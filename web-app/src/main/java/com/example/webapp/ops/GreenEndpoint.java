package com.example.webapp.ops;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "green")
public class GreenEndpoint {

    @ReadOperation
    public String greet() {
        return "Hello Green!";
    }
}
