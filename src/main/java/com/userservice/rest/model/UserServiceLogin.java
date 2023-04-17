package com.userservice.rest.model;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserServiceLogin {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}