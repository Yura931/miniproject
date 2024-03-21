package sideproject.boardservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import sideproject.boardservice.jwt.properties.JwtProperties;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static sideproject.boardservice.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;
import static sideproject.boardservice.jwt.properties.JwtProperties.TOKEN_PREFIX;

/*
* @TestConfiguration
* 테스트 환경에서만 사용되는 설정 클래스 정의
* 애플리케이션 컨텍스트에는 영향을 주지 않고 테스트 스코프에서만 로드 됨
* 테스트 환경에 필요한 Bean 정의, 환경 설정에 유용
* */
@TestConfiguration
public class TestFeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", Arrays.asList("ROLE_ADMIN")
                .stream()
                .map(role -> new SimpleGrantedAuthority("authority"))
                .collect(Collectors.toList()));
        extraClaims.put("id", UUID.randomUUID());
        extraClaims.put("nickname", "nickname");
        String token = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject("id")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 1000 * 24 * 60)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);

    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(JwtProperties.SECRET);
        return Keys.hmacShaKeyFor(key);
    }
}

