package subproject.admin.jwt.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;

    @Builder(access = AccessLevel.PRIVATE)
    private JwtAuthenticationResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public static JwtAuthenticationResponse of(String token, String refreshToken) {
        return JwtAuthenticationResponse
                .builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
