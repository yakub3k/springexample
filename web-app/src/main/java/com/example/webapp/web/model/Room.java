package com.example.webapp.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private UUID id;
    private String name;
    private String number;
    private String info;
}
