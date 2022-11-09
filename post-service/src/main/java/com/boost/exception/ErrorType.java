package com.boost.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    INVALID_TOKEN(2002, "Geçersiz Token", BAD_REQUEST),

    POST_NOT_CREATED(3000, "Post Olusturulamadi", INTERNAL_SERVER_ERROR),
    COMMENT_NOT_CREATED(3001, "Comment Olusturulamadi", INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    HttpStatus httpStatus;

}