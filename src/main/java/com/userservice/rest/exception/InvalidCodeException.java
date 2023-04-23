package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Your code is not valid. Please generate a new one")
public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException() {

        super();
    }
}
