package com.app.appbackend.validation;

import lombok.Getter;

import java.util.List;

@Getter

public class ValidationError {

    public ValidationError(String code, List<String> arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    private String code;
    private List<String> arguments;
}