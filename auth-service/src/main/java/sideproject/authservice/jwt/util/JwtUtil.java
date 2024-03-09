package sideproject.authservice.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sideproject.authservice.global.exception.ExpiredJwtTokenException;
import sideproject.authservice.principal.PrincipalDetails;
import sideproject.authservice.redis.RedisUtil;
import sideproject.authservice.redis.dto.RedisDto;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static sideproject.authservice.jwt.properties.JwtProperties.*;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisUtil redisUtil;

    public String generateToken(PrincipalDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", userDetails.getAuthorities());
        extraClaims.put("id", userDetails.getId());
        extraClaims.put("nickname", userDetails.getNickname());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        extraClaims.put("roles", userDetails.getAuthorities());
        String refreshToken = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        // redis에 저장

        RedisDto refreshDto = RedisDto.of(userDetails.getUsername(), refreshToken);
        redisUtil.set(refreshToken, refreshDto, REFRESH_TOKEN_EXPIRE_TIME);
        return refreshToken;
    }

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

    public String getHeaderAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(AUTHORIZATION_HEADER);
        if (Objects.isNull(headerValue) || StringUtils.isEmpty(headerValue)
            || !org.apache.commons.lang3.StringUtils.startsWith(headerValue, TOKEN_PREFIX)) {
            return "";
        }

        return headerValue.substring(TOKEN_PREFIX.length());
    }


}
