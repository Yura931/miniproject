package sideproject.fileservice.jwt.properties;

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

    @PostConstruct
    public void init() {
        SECRET = secret;
        TOKEN_PREFIX = tokenPrefix;
        REFRESH_PREFIX = refreshPrefix;
        AUTHORITIES_KEY = authoritiesKey;
        AUTHORIZATION_HEADER = authorizationHeader;
        REFRESH_HEADER = refreshHeader;
    }
}
