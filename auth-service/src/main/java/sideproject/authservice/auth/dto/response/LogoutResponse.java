package sideproject.authservice.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LogoutResponse {
    private String message;

    public static LogoutResponse from (String message) {
        return new LogoutResponse(message);
    }
}

