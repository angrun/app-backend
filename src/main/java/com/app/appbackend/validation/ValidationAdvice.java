package com.app.appbackend.validation;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice //see on uks eriline klass, tegeleb uldiste sundmustega
public class ValidationAdvice {

    @ResponseBody
    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrors handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        System.out.println(" I WAS HERE");

        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        System.out.println("YES" + errors);

        ValidationErrors ve = new ValidationErrors();

        for (FieldError error : errors) {
            ve.addError(error);
        }

        return ve;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object processValidationError(NotFoundException ex) {

        return new ValidationError(ex.getErrorMessage(), null);

    }
}
