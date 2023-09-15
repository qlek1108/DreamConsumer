package com.example.demo.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_EXISTS(409, "Email Already Used"),
    USER_NOT_FOUND(404, "User Not Found"),
    USER_NOT_SLEEPING(409, "User NOT SLEEPING");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
