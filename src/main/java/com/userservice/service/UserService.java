package com.userservice.service;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.userservice.rest.model.UserServiceConfirmation;
import com.userservice.rest.model.UserServiceLogin;
import com.userservice.rest.model.UserServiceRegistration;
import com.userservice.rest.model.UserServiceToken;

public interface UserService {

    SignUpResult signUp(UserServiceRegistration user);
    ConfirmSignUpResult confirmSignup(UserServiceConfirmation confirmation);
    UserServiceToken login(UserServiceLogin user);
}
