package sideproject.boardservice.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sideproject.boardservice.global.exception.ExpiredJwtTokenException;
import sideproject.boardservice.global.exception.InvalidTokenException;
import sideproject.boardservice.global.exception.NotFoundTokenFromHeaderException;
import sideproject.boardservice.jwt.enums.ClaimsKey;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static sideproject.boardservice.jwt.enums.ClaimsKey.*;
import static sideproject.boardservice.jwt.properties.JwtProperties.*;


@Component
@RequiredArgsConstructor
public class JwtUtil {

    public Authentication getAuthentication(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get(ROLES.getValue());

        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.get(AUTHORITY.getValue())))
                .toList();

        UserDetails userDetails = new User(getUserId(accessToken), "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, accessToken, authorities);
    }

    public String getNickname(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        return Optional.ofNullable(claims.get(NICKNAME.getValue()).toString()).orElseGet(() -> "");
    }

    public String getUserId(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        return Optional.ofNullable(claims.get(ID.getValue()).toString()).orElseThrow(InvalidTokenException::new);
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
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(headerValue -> headerValue.startsWith(TOKEN_PREFIX))
                .map(token -> token.substring(TOKEN_PREFIX.length()))
                .orElseThrow(NotFoundTokenFromHeaderException::new);
    }
}
