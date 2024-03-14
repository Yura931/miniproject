package sideproject.authservice.jwt.properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    public static String SECRET;
    public static String TOKEN_PREFIX;
    public static String REFRESH_PREFIX;
    public static String AUTHORITIES_KEY;
    public static String AUTHORIZATION_HEADER;
    public static String REFRESH_HEADER;
    public static Long ACCESS_TOKEN_EXPIRE_TIME;
    public static Long REFRESH_TOKEN_EXPIRE_TIME;
    public static String ACCESS_TOKEN_KEY_PREFIX = "access_token:";
    public static String REFRESH_TOKEN_KEY_PREFIX = "refresh_token:";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token_prefix}")
    private String tokenPrefix;

    @Value("${jwt.refresh_prefix}")
    private String refreshPrefix;

    @Value("${jwt.authorities_key}")
    private String authoritiesKey;

    @Value("${jwt.authorization_header}")
    private String authorizationHeader;

    @Value("${jwt.refresh_header}")
    private String refreshHeader;

    @Value("${jwt.access_token_expire_time}")
    private Long accessTokenExpireTime;

    @Value("${jwt.refresh_token_expire_time}")
    private Long refreshTokenExpireTime;

    @PostConstruct
    public void init() {
        SECRET = secret;
        TOKEN_PREFIX = tokenPrefix;
        REFRESH_PREFIX = refreshPrefix;
        AUTHORITIES_KEY = authoritiesKey;
        AUTHORIZATION_HEADER = authorizationHeader;
        REFRESH_HEADER = refreshHeader;
        ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;
        REFRESH_TOKEN_EXPIRE_TIME = refreshTokenExpireTime;
    }
}
