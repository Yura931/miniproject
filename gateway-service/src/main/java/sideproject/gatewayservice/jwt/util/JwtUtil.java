package sideproject.gatewayservice.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import sideproject.gatewayservice.exception.ExpiredJwtTokenException;
import sideproject.gatewayservice.exception.NotFoundTokenFromHeaderException;
import sideproject.gatewayservice.exception.enums.ErrorCode;
import sideproject.gatewayservice.redis.RedisUtil;

import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static sideproject.gatewayservice.jwt.properties.JwtProperties.*;


@Component
@RequiredArgsConstructor
public class JwtUtil {

    public Long getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration).getTime();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtTokenException();
        }

    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String getHeaderAccessToken(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().getOrEmpty(AUTHORIZATION_HEADER).get(0))
                .filter(headerValue -> headerValue.startsWith(TOKEN_PREFIX))
                .map(token -> token.substring(TOKEN_PREFIX.length()))
                .orElseThrow(NotFoundTokenFromHeaderException::new);
    }


}
