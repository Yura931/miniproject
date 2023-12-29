package sideproject.admin.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static sideproject.admin.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;
import static sideproject.admin.jwt.properties.JwtProperties.TOKEN_PREFIX;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = authentication.getCredentials().toString();
        requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
