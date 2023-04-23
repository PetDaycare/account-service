package com.userservice.service;
import com.amazonaws.services.cognitoidp.model.AdminResetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.userservice.rest.model.*;

public interface AccountService {

    SignUpResult signUp(AccountServiceRegistration user);
    ConfirmSignUpResult confirmSignup(AccountServiceConfirmation confirmation);
    AccountServiceToken login(AccountServiceLogin user);
    AdminResetUserPasswordResult resetPassword(String email);
}
