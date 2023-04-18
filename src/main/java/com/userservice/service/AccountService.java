package com.userservice.service;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.userservice.rest.model.AccountServiceConfirmation;
import com.userservice.rest.model.AccountServiceLogin;
import com.userservice.rest.model.AccountServiceRegistration;
import com.userservice.rest.model.AccountServiceToken;

public interface AccountService {

    SignUpResult signUp(AccountServiceRegistration user);
    ConfirmSignUpResult confirmSignup(AccountServiceConfirmation confirmation);
    AccountServiceToken login(AccountServiceLogin user);
}
