package com.petdaycare.userservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.petdaycare.userservice.model.LoginSession;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LoginSessionDTO {

    @NotNull
    private String loginToken;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime expires;

    public LoginSessionDTO(LoginSession session) {

        this.userId = session.getUser().getUserId();
        this.loginToken = session.getLoginToken();
        this.expires = session.getExpires();
    }
}
