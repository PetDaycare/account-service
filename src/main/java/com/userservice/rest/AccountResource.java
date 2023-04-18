package com.userservice.rest;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.userservice.rest.exception.AccountAlreadySignedUpException;
import com.userservice.rest.exception.SignupNotValidException;
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

    @PostMapping("/accounts")
    @Operation(summary = "Register a new account", description = "Registers a new account to amazon cognito using the given information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "An account with this email is already signed up"),
    })
    @ResponseBody
    public void registerUser(
            @Parameter(description = "The Account information to register", required = true) @RequestBody @Valid AccountServiceRegistration newAccount) {

        try {

            accountService.signUp(newAccount);

        } catch (InvalidParameterException e) {

            throw new SignupNotValidException();

        }catch (UsernameExistsException e) {

            throw new AccountAlreadySignedUpException();
        }

    }

    @PostMapping("/accounts/verification")
    @ResponseBody
    public void confirmUser(@RequestBody @Valid AccountServiceConfirmation confirmation) {

        accountService.confirmSignup(confirmation);
    }

    @PostMapping("/users/login")
    @ResponseBody
    public AccountServiceToken loginUser(@RequestBody AccountServiceLogin account) {

        return accountService.login(account);
    }

}
