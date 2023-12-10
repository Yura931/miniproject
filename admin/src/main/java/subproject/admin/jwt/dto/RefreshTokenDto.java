package subproject.admin.jwt.dto;


import jakarta.servlet.http.Cookie;

import java.sql.Ref;

public record RefreshTokenDto(
        String refreshToken
) {

    public static RefreshTokenDto from (String refreshToken) {
        return new RefreshTokenDto(refreshToken);
    }
}


