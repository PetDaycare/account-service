package com.userservice.service.model;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class EmailConfirmation {

    @Email
    private String email;

    @Length(min = 6, max = 6)
    private String confirmationCode;
}
