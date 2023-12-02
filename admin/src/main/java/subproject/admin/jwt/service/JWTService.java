package subproject.admin.jwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.userdetails.UserDetails;
import subproject.admin.global.exception.ExpiredAccessTokenException;
import subproject.admin.global.exception.ExpiredRefreshTokenException;

import java.util.Map;

public interface JWTService {

    String extractUserName(String token);
    Long getExpiration(String token);
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean accessTokenValidateCheck(String token);
    boolean refreshTokenValidateCheck(String refreshToken);
}
