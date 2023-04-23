package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServiceRegistration {

    @JsonProperty(value = "email",access = JsonProperty.Access.WRITE_ONLY)
    @Email(message = "email must be a valid email")
    private String email;

    @JsonProperty(value = "fullname", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "fullname must not me null")
    private String fullName;

    @JsonProperty(value = "accountname", access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 255, message = "Username must be between 6 and 255 characters long")
    private String accountName;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8,max = 255, message = "Password must be between 8 and 255 characters long")
    private String password;
}
