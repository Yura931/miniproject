package sideproject.authservice.auth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sideproject.authservice.auth.dto.RefreshTokenDto;
import sideproject.authservice.auth.dto.SignInDto;
import sideproject.authservice.auth.dto.SignUpDto;
import sideproject.authservice.auth.dto.request.SignUpRequest;
import sideproject.authservice.auth.dto.response.LogoutResponse;
import sideproject.authservice.auth.dto.response.ReIssueAccessTokenResponse;
import sideproject.authservice.auth.dto.response.SignInResponse;
import sideproject.authservice.auth.dto.response.SignUpResponse;
import sideproject.authservice.auth.service.AuthService;
import sideproject.authservice.common.dto.Result;
import sideproject.authservice.common.dto.ResultHandler;
import sideproject.authservice.common.util.CookieUtil;
import sideproject.authservice.global.exception.ExpiredRefreshTokenException;

import static sideproject.authservice.jwt.properties.JwtProperties.REFRESH_PREFIX;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final CookieUtil cookieUtil;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public ResponseEntity home() {
        log.info("auth-service");
        return ResponseEntity.ok().body("auth-service");
    }
    @PostMapping("/api/v1/signUp")
    public ResponseEntity<Result> sighUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse signUpResponse = authService.signUp(SignUpDto.of(request, passwordEncoder));
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "회원가입", signUpResponse));
    }
    @PostMapping("/api/v1/signIn")
    public ResponseEntity<Result> signIn(@Valid @RequestBody SignUpRequest request, HttpServletResponse response) {
        SignInResponse signInResponse = authService.signIn(SignInDto.from(request), response);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "로그인", signInResponse));
    }

    @PostMapping("/api/v1/re-issue")
    public ResponseEntity<Result> reIssueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = cookieUtil.getCookie(request, REFRESH_PREFIX)
                .orElseThrow(ExpiredRefreshTokenException::new);
        RefreshTokenDto dto = RefreshTokenDto.from(cookie.getValue());
        ReIssueAccessTokenResponse reIssueAccessTokenResponse = authService.reIssueAccessToken(dto, response);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "access token 재발급", reIssueAccessTokenResponse));
    }

    @PostMapping("/api/v1/logout")
    public ResponseEntity<Result> logout(HttpServletRequest request, HttpServletResponse response) {
        LogoutResponse logoutResponse = authService.logout(request, response);
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "로그아웃", logoutResponse));
    }
}
