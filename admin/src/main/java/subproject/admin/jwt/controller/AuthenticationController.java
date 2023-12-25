package subproject.admin.jwt.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.common.util.CookieUtil;
import subproject.admin.global.exception.ExpiredRefreshTokenException;
import subproject.admin.jwt.dto.RefreshTokenDto;
import subproject.admin.jwt.dto.response.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.request.SignUpRequest;
import subproject.admin.jwt.dto.request.SigninRequest;
import subproject.admin.jwt.dto.response.SignUpResponse;
import subproject.admin.jwt.principal.PrincipalDetails;
import subproject.admin.jwt.service.AuthenticationService;


import java.sql.Ref;
import java.util.Optional;

import static subproject.admin.jwt.properties.JwtProperties.*;
import static subproject.admin.jwt.properties.JwtProperties.REFRESH_PREFIX;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final CookieUtil cookieUtil;

    @PostMapping("/auth/signup")
    public ResponseEntity<Result> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", signUpResponse));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Result> role(@Valid @RequestBody SigninRequest signinRequest, HttpServletResponse response) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(signinRequest, response);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }

    @PostMapping("/admin/refresh")
    public ResponseEntity<Result> refresh(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> cookie = cookieUtil.getCookie(request, REFRESH_PREFIX);
        RefreshTokenDto dto = RefreshTokenDto.from(
                cookie.map(Cookie::getValue)
                        .orElseThrow(ExpiredRefreshTokenException::new)
        );
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.refreshToken(dto, response);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }
}
