package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServiceRegistration {

    @Email(message = "Field email must contain an email.")
    @JsonProperty(value = "email")
    private String email;

    @NotEmpty
    @JsonProperty(value = "fullname")
    private String fullName;

    @NotEmpty
    @JsonProperty(value = "username")
    private String accountName;

    @JsonProperty(value = "password")
    @NotEmpty
    @Size(min = 8, message = "password must be at least 8 characters long")
    private String password;


}
