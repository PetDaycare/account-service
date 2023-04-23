package com.userservice.rest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED, reason = "This users email has not been confirmed. Please confirm your email address")
public class AccountNotConfirmedException extends RuntimeException {

}
