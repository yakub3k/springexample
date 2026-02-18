package com.example.appclr;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class AsyncPayload {
    private UUID id;
    private String model;
}
