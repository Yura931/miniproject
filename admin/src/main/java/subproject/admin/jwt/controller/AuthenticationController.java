package subproject.admin.jwt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.dto.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.RefreshTokenRequest;
import subproject.admin.jwt.dto.SignUpRequest;
import subproject.admin.jwt.dto.SigninRequest;
import subproject.admin.jwt.service.AuthenticationService;

import static subproject.admin.jwt.properties.JwtProperties.*;
import static subproject.admin.jwt.properties.JwtProperties.REFRESH_PREFIX;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    // 커스텀 Result, ResponseEntity 뭐가 더 좋은 방법??
    @PostMapping("/signup")
    public ResponseEntity<Result> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", authenticationService.signUp(signUpRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<Result> role(@Valid @RequestBody SigninRequest signinRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(signinRequest);
        return ResponseEntity.ok()
                .header(AUTHORIZATION_HEADER, TOKEN_PREFIX + jwtAuthenticationResponse.getToken())
                .header(REFRESH_PREFIX)
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Result> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok()
                .header(AUTHORIZATION_HEADER, TOKEN_PREFIX + jwtAuthenticationResponse.getToken())
                .header(REFRESH_PREFIX)
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }
}
