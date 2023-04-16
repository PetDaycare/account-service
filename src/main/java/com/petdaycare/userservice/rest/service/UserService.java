package com.petdaycare.userservice.rest.service;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.petdaycare.userservice.model.dto.LoginDTO;
import com.petdaycare.userservice.model.dto.LoginResultDTO;
import com.petdaycare.userservice.model.dto.RegisterUserDTO;
import com.petdaycare.userservice.model.dto.UserConfirmationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserService {

    private final AWSCognitoIdentityProvider client;
    @Value("#{environment.clientId}")
    private String clientId;
    @Value("#{environment.userpoolid}")
    private String userpoolId;

    @Autowired
    public UserService(AWSCognitoIdentityProvider client) {

        this.client = client;
    }

    public SignUpResult signUp(RegisterUserDTO user) {

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

    public ConfirmSignUpResult confirmSignup(UserConfirmationDTO confirmation) {

        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withClientId(clientId)
                .withUsername(confirmation.getEmail())
                .withConfirmationCode(confirmation.getConfirmationCode());
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public LoginResultDTO login(LoginDTO user) {

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

        return new LoginResultDTO(resultType, "Successfully logged in");
    }
}

