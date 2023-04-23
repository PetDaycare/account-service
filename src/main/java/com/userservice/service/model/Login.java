package com.userservice.service.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Login {

    @Email
    private String email;
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters long")
    private String password;
}
