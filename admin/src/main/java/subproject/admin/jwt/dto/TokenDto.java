package subproject.admin.jwt.dto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import subproject.admin.global.exception.NotFoundTokenFromHeaderException;
import subproject.admin.jwt.properties.JwtProperties;
import subproject.admin.jwt.service.JWTService;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

import static subproject.admin.jwt.properties.JwtProperties.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenDto {
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public static TokenDto toJwtToken(HttpServletRequest request) {
        String accessToken = getHeaderAccessToken(request, AUTHORIZATION_HEADER);
        return new TokenDto(accessToken);
    }

    private static String getHeaderAccessToken(HttpServletRequest request, String headerName) {
        String headerValue = request.getHeader(headerName);
        if (Objects.isNull(headerValue) || StringUtils.isEmpty(headerValue)
            || !org.apache.commons.lang3.StringUtils.startsWith(headerValue, TOKEN_PREFIX)) {
            return "";
        }
        return headerValue.substring(TOKEN_PREFIX.length());
    }

}
