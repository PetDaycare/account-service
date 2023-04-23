package com.userservice.service;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.userservice.rest.exception.AccountWithEmailDoesntExistException;
import com.userservice.rest.model.*;
import com.userservice.service.model.EmailConfirmation;
import com.userservice.service.model.Login;
import com.userservice.service.model.PasswordResetConfirmation;
import com.userservice.service.model.Registration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AccountServiceImpl implements AccountService {

    private final AWSCognitoIdentityProvider client;
    @Value("#{environment.clientId}")
    private String clientId;
    @Value("#{environment.userpoolid}")
    private String userpoolId;

    @Autowired
    public AccountServiceImpl(AWSCognitoIdentityProvider client) {

        this.client = client;
    }

    public SignUpResult signUp(@Valid Registration user) {

        SignUpRequest request = new SignUpRequest().withClientId(clientId)
                .withUsername(user.getEmail())
                .withPassword(user.getPassword())
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(user.getFullName())
                                .withName("nickname").withValue(user.getAccountName())
                );
        return client.signUp(request);
    }

    public ConfirmSignUpResult confirmSignup(@Valid EmailConfirmation emailConfirmation) {

        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withClientId(clientId)
                .withUsername(emailConfirmation.getEmail())
                .withConfirmationCode(emailConfirmation.getConfirmationCode());
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public AccountServiceToken login(Login login) {

        Map<String, String> authParams = new LinkedHashMap<>();
        authParams.put("USERNAME", login.getEmail());
        authParams.put("PASSWORD", login.getPassword());

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(userpoolId)
                .withClientId(clientId)
                .withAuthParameters(authParams);

        AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();

        return new AccountServiceToken(resultType, "Successfully logged in");
    }

    @Override
    public AdminResetUserPasswordResult resetPassword(@Email @Valid String email) {

        AdminResetUserPasswordRequest request = new AdminResetUserPasswordRequest()
                .withUserPoolId(userpoolId)
                .withUsername(email);
        AdminResetUserPasswordResult result = client.adminResetUserPassword(request);

        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200){

            throw new AccountWithEmailDoesntExistException();
        }

        return result;
    }

    @Override
    public ConfirmForgotPasswordResult setPassword(PasswordResetConfirmation passwordResetConfirmation) {

        ConfirmForgotPasswordRequest request = new ConfirmForgotPasswordRequest()
                .withPassword(passwordResetConfirmation.getPassword())
                .withConfirmationCode(passwordResetConfirmation.getResetCode())
                .withUsername(passwordResetConfirmation.getEmail())
                .withClientId(clientId);
        return client.confirmForgotPassword(request);
    }
}

