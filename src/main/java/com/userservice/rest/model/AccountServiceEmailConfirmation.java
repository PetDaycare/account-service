package com.userservice.rest.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class AccountServiceEmailConfirmation {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 6, max = 6)
    private String confirmationCode;

}
