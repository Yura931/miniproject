package sideproject.authservice.auth.dto;


public record RefreshTokenDto(
        String refreshToken
) {

    public static RefreshTokenDto from (String refreshToken) {
        return new RefreshTokenDto(refreshToken);
    }
}


