package sideproject.gatewayservice.jwt.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import sideproject.gatewayservice.common.dto.ResultHandler;
import sideproject.gatewayservice.config.RouterValidator;
import sideproject.gatewayservice.exception.ExpiredJwtTokenException;
import sideproject.gatewayservice.exception.ExpiredRefreshTokenException;
import sideproject.gatewayservice.exception.LogoutTokenRequestException;
import sideproject.gatewayservice.exception.NotFoundTokenFromHeaderException;
import sideproject.gatewayservice.exception.enums.ErrorCode;
import sideproject.gatewayservice.jwt.util.JwtUtil;
import sideproject.gatewayservice.redis.RedisUtil;

import java.nio.charset.StandardCharsets;

import static sideproject.gatewayservice.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements WebFilter {

    private final RouterValidator validator;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();


        if(validator.isSecured.test(request)) {

            if (authMissing(request)) {
                return onError(response, ErrorCode.UNAUTHORIZED);
            }


            final String accessToken = jwtUtil.getHeaderAccessToken(request);

            if(redisUtil.hasKeyBlackList(accessToken)) {
                return onError(response, ErrorCode.LOGOUT_TOKEN);
            };

            try {
                jwtUtil.extractUserName(accessToken);
            } catch (ExpiredRefreshTokenException ex) {
                return onError(response, ErrorCode.EXPIRED_REFRESH_TOKEN);
            } catch (ExpiredJwtTokenException ex) {
                return onError(response, ErrorCode.EXPIRED_JWT_TOKEN);
            } catch (LogoutTokenRequestException ex) {
                return onError(response, ErrorCode.LOGOUT_TOKEN);
            } catch (NotFoundTokenFromHeaderException ex) {
                return onError(response, ErrorCode.HEADER_TOKEN_NOT_FOUND);
            } catch (JwtException ex) {
                return onError(response, ErrorCode.INVALID_TOKEN);
            }
        }


        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerHttpResponse response, ErrorCode errorCode) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String result = "";
        try {
            result = objectMapper.writeValueAsString(ResultHandler.errorHandle(errorCode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(AUTHORIZATION_HEADER);
    }

}
