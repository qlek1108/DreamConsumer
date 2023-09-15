package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMyPageResponseDto {
    private long userId;

    private String userName;

    private String email;

    private String job;

    private int age;

    // 등급

    //성공한 item 개수

    // 성공한 items pagination
}
