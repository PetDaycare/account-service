package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServicePasswordResetConfirmation {

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8,max = 255, message = "Password must be between 8 and 255 characters long")
    private String password;
    @JsonProperty(value = "confirmationcode", access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6,max = 6, message = "Confirmation code must be 6 characters long")
    private String resetCode;

}
