package com.example.appclr;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Room {
    private UUID id;
    private String name;
    private String number;
    private String info;
}
