package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private long userId;

    private String userName;

    private String email;

    private String password;

    private String job;

    private int age;

    private boolean emailAcceptance;
}
