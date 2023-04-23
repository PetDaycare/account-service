package com.userservice.service.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Registration {

    @Email(message = "email must be a valid email")
    private String email;

    private String fullName;

    @Size(min = 6, max = 255, message = "Username must be between 6 and 255 characters long")
    private String accountName;

    @Size(min = 8,max = 255, message = "Password must be between 8 and 255 characters long")
    private String password;
}
