package sideproject.boardservice.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sideproject.boardservice.jwt.util.JwtUtil;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String accessToken = jwtUtil.getHeaderAccessToken(request);
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .ifPresentOrElse(auth -> {
                    UserDetails userDetails = (UserDetails) auth.getPrincipal();
                    String username = userDetails.getUsername();
                    String nickname = jwtUtil.getNickname(accessToken);

                    if (!username.equals(nickname)) {

                    }

                }, () -> {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    Authentication authentication = jwtUtil.getAuthentication(accessToken);
                    securityContext.setAuthentication(authentication);
                    SecurityContextHolder.setContext(securityContext);
                });

        filterChain.doFilter(request, response);
    }
}
