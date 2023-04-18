package com.userservice.rest.model;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountServiceToken {

    @NotEmpty
    private String idToken;

    @NotEmpty
    private String accessToken;

    @NotEmpty
    private String refreshToken;

    private String message;

    public AccountServiceToken(AuthenticationResultType resultType, String message) {

        this.idToken= resultType.getIdToken();
        this.accessToken = resultType.getAccessToken();
        this.refreshToken = resultType.getRefreshToken();
        this.message = message;
    }
}
