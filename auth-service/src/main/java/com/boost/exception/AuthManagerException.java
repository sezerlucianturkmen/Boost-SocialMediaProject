package com.boost.exception;
import lombok.Getter;

@Getter
public class AuthManagerException extends RuntimeException{
    private final ErrorType errorType;

    public AuthManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AuthManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}