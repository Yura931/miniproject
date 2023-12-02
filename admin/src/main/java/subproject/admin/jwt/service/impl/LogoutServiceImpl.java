package subproject.admin.jwt.service.impl;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Optionals;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.redis.RedisUtil;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static subproject.admin.jwt.properties.JwtProperties.AUTHORIZATION_HEADER;
import static subproject.admin.jwt.properties.JwtProperties.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler {

    private final JWTService jwtService;
    private final RedisUtil redisUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = TokenDto.toJwtToken(request).getToken();
        String userEmail = jwtService.extractUserName(jwt);
        if (Boolean.FALSE.equals(Objects.isNull(redisUtil.get(userEmail)))) {
            // refreshToken 삭제
            redisUtil.delete(userEmail);
        }
        // redis accessToken blackList 등록
        Long getExpiration = jwtService.getExpiration(jwt) - new Date().getTime();
        redisUtil.setBlackList(userEmail, jwt, getExpiration.intValue());
    }

}
