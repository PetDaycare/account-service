package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "The account credentials aren't valid")
public class SignupNotValidException extends RuntimeException {

    public SignupNotValidException(){

        super();
    }
}
