package com.petdaycare.userservice.model.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}