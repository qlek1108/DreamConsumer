package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserPostDto {

    @NotBlank
    private String userName;

    @NotBlank
    @NotNull
    @Email
    private String email;

    //Pattern 필요
    @NotBlank
    private String password;

    @Pattern(regexp = "^(무직|프리랜서|학생|회사원)$", message = "Invalid job type")
    private String job;

    @Pattern(regexp = "^(1[5-9]|[2-9][0-9]|100)$", message = "Age must be between ?? and ??")
    private int age;

    @NotBlank
    private boolean emailAcceptance;
}
