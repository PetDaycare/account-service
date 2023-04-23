package com.userservice.rest;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.userservice.rest.exception.AccountAlreadySignedUpException;
import com.userservice.rest.exception.SignupNotValidException;
import com.userservice.rest.model.*;
import com.userservice.service.AccountService;
import com.userservice.service.model.EmailConfirmation;
import com.userservice.service.model.Login;
import com.userservice.service.model.PasswordResetConfirmation;
import com.userservice.service.model.Registration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
public class AccountResource {

    private AccountService accountService;
    private ModelMapper modelMapper;

    @Autowired
    public AccountResource(AccountService accountService) {

        this.accountService = accountService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/accounts")
    @Operation(summary = "Register a new account", description = "Registers a new account to amazon cognito using the given information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "An account with this email is already signed up"),
    })

    public void registerUser(@Parameter(description = "The Account information to register", required = true) @RequestBody @Valid AccountServiceRegistration newAccount) {


        Registration registration = modelMapper.map(newAccount, Registration.class);

        try {

            accountService.signUp(registration);

        } catch (InvalidParameterException e) {

            throw new SignupNotValidException();

        } catch (UsernameExistsException e) {

            throw new AccountAlreadySignedUpException();
        }

    }

    @PostMapping("/accounts/{email}/verification")
    public void confirmUser(@PathVariable @Email @Valid String email, @RequestBody @Valid AccountServiceEmailConfirmation confirmation) {

        EmailConfirmation emailConfirmation = modelMapper.map(confirmation, EmailConfirmation.class);
        emailConfirmation.setEmail(email);

        accountService.confirmSignup(emailConfirmation);
    }

    @PostMapping("/accounts/{email}/login")
    public AccountServiceToken loginUser(@PathVariable @Email @Valid String email, @RequestBody @Valid AccountServiceLogin account) {

        Login login = modelMapper.map(account, Login.class);
        login.setEmail(email);

        return accountService.login(login);
    }

    @GetMapping("accounts/{email}/password")
    public void getPasswordReset(@PathVariable  @Email @Valid String email) {

        accountService.resetPassword(email);
    }

    @PostMapping("accounts/{email}/password")
    public void confirmPasswordReset(@PathVariable @Email @Valid String email, @RequestBody @Valid AccountServicePasswordResetConfirmation confirmation) {

        PasswordResetConfirmation passwordResetConfirmation = modelMapper.map(confirmation, PasswordResetConfirmation.class);
        passwordResetConfirmation.setEmail(email);

        accountService.setPassword(passwordResetConfirmation);
    }
}
