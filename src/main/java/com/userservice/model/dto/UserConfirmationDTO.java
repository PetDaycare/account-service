package com.userservice.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserConfirmationDTO {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String confirmationCode;

}
