package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateRequestDto {
    private UUID id;

    private String name;

    private int age;
}
