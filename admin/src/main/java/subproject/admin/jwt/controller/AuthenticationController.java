package subproject.admin.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.dto.response.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.request.RefreshTokenRequest;
import subproject.admin.jwt.dto.request.SignUpRequest;
import subproject.admin.jwt.dto.request.SigninRequest;
import subproject.admin.jwt.service.AuthenticationService;


import static subproject.admin.jwt.properties.JwtProperties.*;
import static subproject.admin.jwt.properties.JwtProperties.REFRESH_PREFIX;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

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
                .header(REFRESH_HEADER, REFRESH_PREFIX + jwtAuthenticationResponse.getRefreshToken())
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Result> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok()
                .header(AUTHORIZATION_HEADER, TOKEN_PREFIX + jwtAuthenticationResponse.getToken())
                .header(REFRESH_HEADER, REFRESH_PREFIX + jwtAuthenticationResponse.getRefreshToken())
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", jwtAuthenticationResponse));
    }
}
