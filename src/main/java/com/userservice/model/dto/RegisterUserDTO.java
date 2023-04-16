package com.userservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDTO {

    @Email(message = "Field email must contain an email.")
    @JsonProperty(value = "email")
    private String email;

    @NotEmpty
    @JsonProperty(value = "fullname")
    private String fullName;

    @NotEmpty
    @JsonProperty(value = "username")
    private String userName;

    @JsonProperty(value = "password")
    private String password;


}
