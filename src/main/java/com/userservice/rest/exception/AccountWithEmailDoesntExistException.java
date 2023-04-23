package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.GONE, reason= "An account with this email doesn't exist.")
public class AccountWithEmailDoesntExistException extends RuntimeException {

    public AccountWithEmailDoesntExistException() {

        super();
    }
}
