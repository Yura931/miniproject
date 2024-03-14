package sideproject.authservice.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sideproject.authservice.jwt.util.JwtUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final String[] BLACK_LIST = {
            "/api/v1/logout",
            "/api/v1/re-issue",
            "/management/",
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isProtectedPath(request.getRequestURI())) {
            final String accessToken = jwtUtil.getHeaderAccessToken(request);
            Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                    .ifPresentOrElse(context -> {
                            },
                            () -> {
                                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                                Authentication authentication = jwtUtil.getAuthentication(accessToken);
                                securityContext.setAuthentication(authentication);
                                SecurityContextHolder.setContext(securityContext);
                            });
        }
        filterChain.doFilter(request, response);
    }

    private boolean isProtectedPath(String requestURI) {
        return Arrays.stream(BLACK_LIST)
                .anyMatch(list -> list.equals(requestURI) || list.startsWith(requestURI));
    }
}
