package subproject.admin.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Optionals;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import subproject.admin.common.enums.ErrorCode;
import subproject.admin.global.exception.ExpiredJwtTokenException;
import subproject.admin.global.exception.LogoutTokenRequestException;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.jwt.service.UserService;
import subproject.admin.redis.RedisUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.*;
import static subproject.admin.jwt.properties.JwtProperties.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    private final RedisUtil redisUtil;

    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // header에서 accessToken, refreshToken 찾아서 DTO 생성
        TokenDto jwtToken = TokenDto.toJwtToken(request);
        final String accessToken = jwtToken.getToken();
        final String refreshToken = jwtToken.getRefreshToken();

        if (StringUtils.isEmpty(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtService.accessTokenValidateCheck(accessToken);
        final String userEmail = jwtService.extractUserName(accessToken);
        // logout 된 토큰으로 요청 시 Exception 처리
        logoutTokenCheck(userEmail, accessToken);

        Optional.ofNullable(userEmail)
                .filter((String email) -> Objects.isNull(SecurityContextHolder.getContext().getAuthentication()))
                    .ifPresent((String email) -> {
                        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
                        Optional.of(jwtService.isTokenValid(accessToken, userDetails))
                                .filter((Boolean valid) -> valid)
                                .ifPresent((Boolean validResult) -> {
                                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities()
                                    );

                                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    securityContext.setAuthentication(token);
                                    SecurityContextHolder.setContext(securityContext);
                                });

                        Optional.of(refreshToken)
                                .ifPresent((String token) -> {
                                    Optional.of(Boolean.FALSE.equals(jwtService.isTokenValid(accessToken, userDetails))
                                                    && Boolean.FALSE.equals(jwtService.isTokenValid(token, userDetails)))
                                            .filter((Boolean valid) -> Boolean.TRUE.equals(valid))
                                            .ifPresent((Boolean validResult) -> {
                                                throw new ExpiredJwtTokenException();
                                            });
                                });

                    });


        filterChain.doFilter(request, response);
    }

    private void logoutTokenCheck(String userEmail, String accessToken) {
        Optional.of(redisUtil.getBlackList(userEmail))
                .ifPresent(blackListToken -> {
                    if (accessToken.equals(blackListToken)) {
                        throw new LogoutTokenRequestException(ErrorCode.LOGOUT_TOKEN);
                    }
                });

    }

}
