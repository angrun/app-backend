package com.app.appbackend.validation;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


public class ValidationErrors {


    @Getter
    private List<ValidationError> errors = new ArrayList<>();

    public ValidationErrors addError(FieldError fieldError) {
//        List<String> args = Stream.of(fieldError.getArguments())
//                .filter(arg -> !(arg instanceof DefaultMessageSourceResolvable))
//                .map(String::valueOf).collect(Collectors.toList());

        errors.add(new ValidationError(fieldError.getDefaultMessage(), Integer.parseInt(fieldError.getCode())));

        return this;
    }
}
