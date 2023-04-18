package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason= "An account with this email is already signed up.")
public class AccountAlreadySignedUpException extends RuntimeException {

    public AccountAlreadySignedUpException() {

        super();
    }
}
