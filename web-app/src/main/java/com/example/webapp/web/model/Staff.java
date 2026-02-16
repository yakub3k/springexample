package com.example.webapp.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private UUID id;
    private String firstName;
    private String lastName;
    private String position;
}
