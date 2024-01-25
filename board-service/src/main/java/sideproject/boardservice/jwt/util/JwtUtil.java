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
import sideproject.boardservice.common.exception.ExpiredJwtTokenException;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static sideproject.boardservice.jwt.properties.JwtProperties.*;


@Component
@RequiredArgsConstructor
public class JwtUtil {

    public Authentication getAuthentication(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        List<Map<String, String>> roles = new ArrayList<>();
        roles = (List<Map<String, String>>) claims.get("roles");

        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.get("authority")))
                .toList();

        UserDetails userDetails = new User(getNickname(accessToken), "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, accessToken, authorities);
    }

    public String getNickname(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        return Optional.ofNullable(claims.get("nickname").toString()).orElseGet(() -> "");
    }

    public String getMemberId(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        return Optional.ofNullable(claims.get("id").toString()).orElseGet(() -> "");
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
