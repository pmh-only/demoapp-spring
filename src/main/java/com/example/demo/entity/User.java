package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class User {
    @Builder.Default
    private UUID id = UUID.randomUUID();

    private String name;

    private int age;
}
