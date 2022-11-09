package com.boost.exception;
import lombok.Getter;

@Getter
public class PostManagerException extends RuntimeException{
    private final ErrorType errorType;

    public PostManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public PostManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}