package com.userservice.service;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.userservice.rest.model.UserServiceConfirmation;
import com.userservice.rest.model.UserServiceLogin;
import com.userservice.rest.model.UserServiceRegistration;
import com.userservice.rest.model.UserServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService{

    private final AWSCognitoIdentityProvider client;
    @Value("#{environment.clientId}")
    private String clientId;
    @Value("#{environment.userpoolid}")
    private String userpoolId;

    @Autowired
    public UserServiceImpl(AWSCognitoIdentityProvider client) {

        this.client = client;
    }

    public SignUpResult signUp(UserServiceRegistration user) {

        SignUpRequest request = new SignUpRequest().withClientId(clientId)
                .withUsername(user.getEmail())
                .withPassword(user.getPassword())
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(user.getFullName())
                                .withName("nickname").withValue(user.getUserName())
                );
        return client.signUp(request);
    }

    public ConfirmSignUpResult confirmSignup(UserServiceConfirmation confirmation) {

        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withClientId(clientId)
                .withUsername(confirmation.getEmail())
                .withConfirmationCode(confirmation.getConfirmationCode());
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public UserServiceToken login(UserServiceLogin user) {

        Map<String, String> authParams = new LinkedHashMap<>();
        authParams.put("USERNAME", user.getEmail());
        authParams.put("PASSWORD", user.getPassword());

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(userpoolId)
                .withClientId(clientId)
                .withAuthParameters(authParams);

        AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();

        return new UserServiceToken(resultType, "Successfully logged in");
    }
}

