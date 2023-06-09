package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServiceLogin {


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8,max = 255, message = "Password must be between 8 and 255 characters long")
    private String password;
}
