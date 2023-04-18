package com.userservice.rest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.userservice.rest.exception.SignupNotValidException;
import com.userservice.rest.exception.AccountAlreadySignedUpException;
import com.userservice.rest.model.AccountServiceConfirmation;
import com.userservice.rest.model.AccountServiceLogin;
import com.userservice.rest.model.AccountServiceRegistration;
import com.userservice.rest.model.AccountServiceToken;
import com.userservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class AccountResource {

    AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {

        this.accountService = accountService;
    }

    @PostMapping("/users")
    @Operation(summary = "Register a new user", description = "Registers a new user to amazon cognito using the given information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @ResponseBody
    public SignUpResult registerUser(
            @Parameter(description = "The user information to register", required = true) @RequestBody @Valid AccountServiceRegistration newUser) {

        try {

            return accountService.signUp(newUser);

        } catch (InvalidParameterException e) {

            throw new SignupNotValidException();

        }catch (UsernameExistsException e) {

            throw new AccountAlreadySignedUpException();
        }

    }

    @PostMapping("/users/verification")
    @ResponseBody
    public ConfirmSignUpResult confirmUser(@RequestBody AccountServiceConfirmation confirmation) {

        return accountService.confirmSignup(confirmation);
    }

    @PostMapping("/users/login")
    @ResponseBody
    public AccountServiceToken loginUser(@RequestBody AccountServiceLogin user) {

        return accountService.login(user);
    }

}
