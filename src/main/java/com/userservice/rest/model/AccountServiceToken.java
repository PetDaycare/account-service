package com.userservice.rest.model;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServiceToken {

    @NotEmpty
    @JsonProperty(value = "idtoken")
    private String idToken;

    @NotEmpty
    @JsonProperty(value = "accesstoken")
    private String accessToken;

    @NotEmpty
    @JsonProperty(value = "refreshtoken")
    private String refreshToken;

    @JsonProperty(value = "message")
    private String message;

    public AccountServiceToken(AuthenticationResultType resultType, String message) {

        this.idToken= resultType.getIdToken();
        this.accessToken = resultType.getAccessToken();
        this.refreshToken = resultType.getRefreshToken();
        this.message = message;
    }
}
