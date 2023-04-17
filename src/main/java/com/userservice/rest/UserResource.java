package com.userservice.rest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.userservice.rest.exception.SignupNotValidException;
import com.userservice.rest.exception.UserAlreadySignedUpException;
import com.userservice.rest.model.UserServiceConfirmation;
import com.userservice.rest.model.UserServiceLogin;
import com.userservice.rest.model.UserServiceRegistration;
import com.userservice.rest.model.UserServiceToken;
import com.userservice.service.UserService;
import com.userservice.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserResource {

    UserService userService;

    @Autowired
    public UserResource(UserServiceImpl userService) {

        this.userService = userService;
    }

    @PostMapping("/users")
    @Operation(summary = "Register a new user", description = "Registers a new user to amazon cognito using the given information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @ResponseBody
    public SignUpResult registerUser(
            @Parameter(description = "The user information to register", required = true) @RequestBody @Valid UserServiceRegistration newUser) {

        try {

            return userService.signUp(newUser);

        } catch (InvalidParameterException e) {

            throw new SignupNotValidException();

        }catch (UsernameExistsException e) {

            throw new UserAlreadySignedUpException();
        }

    }

    @PostMapping("/users/verification")
    @ResponseBody
    public ConfirmSignUpResult confirmUser(@RequestBody UserServiceConfirmation confirmation) {

        return userService.confirmSignup(confirmation);
    }

    @PostMapping("/users/login")
    @ResponseBody
    public UserServiceToken loginUser(@RequestBody UserServiceLogin user) {

        return userService.login(user);
    }

}
