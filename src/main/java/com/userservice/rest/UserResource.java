package com.userservice.rest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.userservice.model.dto.LoginDTO;
import com.userservice.model.dto.LoginResultDTO;
import com.userservice.model.dto.RegisterUserDTO;
import com.userservice.model.dto.UserConfirmationDTO;
import com.userservice.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {


    UserService userService;

    @Autowired
    public UserResource(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    public SignUpResult registerUser(@RequestBody RegisterUserDTO newUser){

       return userService.signUp(newUser);
    }

    @PostMapping("/users/verification")
    @ResponseBody
    public ConfirmSignUpResult confirmUser(@RequestBody UserConfirmationDTO confirmation){

        return userService.confirmSignup(confirmation);
    }

    @PostMapping("/users/login")
    @ResponseBody
    public LoginResultDTO loginUser(@RequestBody LoginDTO user){

        return userService.login(user);
    }


}
