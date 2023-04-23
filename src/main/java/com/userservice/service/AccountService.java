package com.userservice.service;
import com.amazonaws.services.cognitoidp.model.AdminResetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.userservice.rest.model.AccountServiceToken;
import com.userservice.service.model.EmailConfirmation;
import com.userservice.service.model.Login;
import com.userservice.service.model.PasswordResetConfirmation;
import com.userservice.service.model.Registration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public interface AccountService {

    SignUpResult signUp(@Valid Registration user);
    ConfirmSignUpResult confirmSignup(@Valid EmailConfirmation emailConfirmation);
    AccountServiceToken login(@Valid Login login);
    AdminResetUserPasswordResult resetPassword(@Email @Valid String email);

    ConfirmForgotPasswordResult setPassword(@Valid PasswordResetConfirmation passwordResetConfirmation);
}
