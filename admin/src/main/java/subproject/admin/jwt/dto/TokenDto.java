package subproject.admin.jwt.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.global.exception.InvalidTokenException;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static subproject.admin.jwt.properties.JwtProperties.*;
import static subproject.admin.jwt.properties.JwtProperties.REFRESH_PREFIX;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(of = { "grantType", "accessToken", "accessTokenExpiresIn", "refreshToken" })
public class TokenDto {
    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;
    private Key key;    // 생성 시 동일한 서명 key 사용

    private TokenDto(String grantType, String accessToken, long accessTokenExpiresIn, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public static TokenDto createToken(String grantType, String accessToken, long accessTokenExpiresIn, String refreshToken) {
        return new TokenDto(grantType, accessToken, accessTokenExpiresIn, refreshToken);
    }

    public void checkValidateJwtToken() {
        if (accessToken == null || accessToken.isEmpty() || !accessToken.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException("잘못된 토큰 정보입니다.");
        }
    }

    public void checkValidateRefreshToken() {
        if (refreshToken == null || refreshToken.isEmpty() || refreshToken.startsWith(REFRESH_PREFIX)) {
            throw new InvalidTokenException("잘못된 토큰 정보입니다.");
        }
    }

    // token으로 사용자 id조회
    public String getUsernameFromToken() {
        return getClaimFromToken(Claims::getId);
    }

    // token으로 사용자 속성정보 조회
    public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken();
        return claimsResolver.apply(claims);
    }

    // 모든 token에 대한 사용자 속성정보 조회
    public Claims getAllClaimsFromToken() {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }
    public boolean isAccessTokenValid() {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken.replace(TOKEN_PREFIX, ""));
        if(claims.getBody().getExpiration().before(new Date()))
            return false;

        return true;
    }

    public boolean isRefreshTokenValid() {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken.replace(REFRESH_PREFIX, ""));

        if(claims.getBody().getExpiration().before(new Date()))
            return false;

        return true;
    }
}
