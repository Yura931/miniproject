package subproject.admin.jwt.dto.response;

import jakarta.servlet.http.Cookie;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtAuthenticationResponse {
    private String token;
    private JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public static JwtAuthenticationResponse from(String token) {
        return new JwtAuthenticationResponse(token);
    }
}
