package com.userservice.model.dto;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResultDTO {

    @NotEmpty
    private String idToken;

    @NotEmpty
    private String accessToken;

    @NotEmpty
    private String refreshToken;

    private String message;

    public LoginResultDTO(AuthenticationResultType resultType, String message) {

        this.idToken= resultType.getIdToken();
        this.accessToken = resultType.getAccessToken();
        this.refreshToken = resultType.getRefreshToken();
        this.message = message;
    }
}
