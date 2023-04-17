package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason= "A user with this email is already signed up.")
public class UserAlreadySignedUpException extends RuntimeException {

    public UserAlreadySignedUpException() {

        super();
    }
}
