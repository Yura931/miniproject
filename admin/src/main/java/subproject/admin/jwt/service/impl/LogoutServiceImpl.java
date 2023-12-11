package subproject.admin.jwt.service.impl;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Optionals;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import subproject.admin.common.util.CookieUtil;
import subproject.admin.global.exception.ExpiredRefreshTokenException;
import subproject.admin.jwt.dto.RefreshTokenDto;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.jwt.properties.JwtProperties;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.redis.RedisUtil;
import subproject.admin.redis.dto.RedisDto;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static subproject.admin.jwt.properties.JwtProperties.*;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler {

    private final JWTService jwtService;
    private final RedisUtil redisUtil;
    private final CookieUtil cookieUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String accessToken = TokenDto.toJwtToken(request).getToken();
        String userEmail = jwtService.extractUserName(accessToken);

        Optional<Cookie> cookie = cookieUtil.getCookie(request, REFRESH_PREFIX);
        RefreshTokenDto dto = RefreshTokenDto.from(
                cookie.map(Cookie::getValue)
                        .orElseThrow(ExpiredRefreshTokenException::new)
        );

        if (Boolean.FALSE.equals(Objects.isNull(redisUtil.get(dto.refreshToken())))) {
            // refreshToken 삭제
            redisUtil.delete(dto.refreshToken());
        }
        // redis accessToken blackList 등록
        Long getExpiration = jwtService.getExpiration(accessToken) - new Date().getTime();
        RedisDto accessTokenDto = RedisDto.of(userEmail, accessToken);
        redisUtil.setBlackList(accessToken, accessTokenDto, getExpiration.intValue());
        
        // cookie 삭제
        cookieUtil.deleteCookie(request, response, REFRESH_PREFIX);
    }

}
