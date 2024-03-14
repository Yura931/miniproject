package sideproject.gatewayservice.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sideproject.gatewayservice.common.dto.ResultHandler;
import sideproject.gatewayservice.exception.ExpiredJwtTokenException;
import sideproject.gatewayservice.exception.ExpiredRefreshTokenException;
import sideproject.gatewayservice.exception.NotFoundTokenFromHeaderException;
import sideproject.gatewayservice.exception.enums.ErrorCode;
import sideproject.gatewayservice.jwt.util.JwtUtil;
import sideproject.gatewayservice.redis.RedisUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static sideproject.gatewayservice.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final ObjectMapper objectMapper;

    public static class Config {

    }

    public AuthorizationHeaderFilter(JwtUtil jwtUtil, RedisUtil redisUtil, ObjectMapper objectMapper) {
        super(Config.class); // Configuration 정보를 필터에 적용할 수 있는 부가 정보로서 캐스팅 시켜주는 작업을 부모클래스에 전달해주어야 함
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            try {

                final String accessToken = jwtUtil.getHeaderAccessToken(request);

                if(redisUtil.hasKeyBlackList(accessToken))
                    return onError(response, ErrorCode.INVALID_TOKEN);

                jwtUtil.extractUserName(accessToken);

            } catch (NotFoundTokenFromHeaderException ex) {
                return onError(response, ErrorCode.NOT_FOUND_HEADER_TOKEN);
            } catch (ExpiredRefreshTokenException ex) {
                return onError(response, ErrorCode.EXPIRED_REFRESH_TOKEN);
            } catch (ExpiredJwtTokenException ex) {
                return onError(response, ErrorCode.EXPIRED_JWT_TOKEN);
            } catch (JwtException ex) {
                return onError(response, ErrorCode.INVALID_TOKEN);
            }

            return chain.filter(exchange);
        });
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
