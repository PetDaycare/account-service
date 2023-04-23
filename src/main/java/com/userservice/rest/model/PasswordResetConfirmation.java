package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordResetConfirmation {

    @JsonProperty("password")
    @NotEmpty
    private String password;
    @JsonProperty("confirmationcode")
    @Min(6)
    @Max(6)
    private String resetCode;

}
