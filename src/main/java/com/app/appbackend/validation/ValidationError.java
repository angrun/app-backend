package com.app.appbackend.validation;

import lombok.Getter;


@Getter
public class ValidationError {

    public ValidationError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    private Integer code;
    private String message;
}