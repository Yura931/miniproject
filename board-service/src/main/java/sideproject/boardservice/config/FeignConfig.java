package sideproject.boardservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static sideproject.boardservice.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;
import static sideproject.boardservice.jwt.properties.JwtProperties.TOKEN_PREFIX;


@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .ifPresent(authentication -> {
                    String token = authentication.getCredentials().toString();
                    requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
                });
    }
}
