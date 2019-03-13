package com.app.appbackend.exceptions;

import lombok.Getter;

@Getter
public class InvalidUserException extends Exception {

    private int errorCode;
    private String errorMessage;

    public InvalidUserException(String message, int errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
