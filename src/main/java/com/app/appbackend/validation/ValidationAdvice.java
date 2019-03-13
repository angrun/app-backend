package com.app.appbackend.validation;


import com.app.appbackend.exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class ValidationAdvice {

    @ResponseBody
    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Set<ObjectError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        Set<ObjectError> ve;

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ve = new HashSet<>(errors);

        return ve;
    }

    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Set<ObjectError> processValidationError(InvalidUserException ex) {

        Set<ObjectError> ve = new HashSet<>();

        ve.add(new ObjectError(ex.getErrorMessage(), ex.getErrorMessage()));

        return ve;

    }
}
