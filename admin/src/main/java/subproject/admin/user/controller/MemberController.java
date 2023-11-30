package subproject.admin.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.dto.SignUpRequest;
import subproject.admin.jwt.dto.SigninRequest;
import subproject.admin.jwt.service.AuthenticationService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class MemberController {
    private final AuthenticationService authenticationService;

    // 커스텀 Result, ResponseEntity 뭐가 더 좋은 방법??
    @PostMapping("/signup")
    public ResponseEntity<Result> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", authenticationService.signUp(signUpRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<Result> role(@Valid @RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok()
                .body(ResultHandler.handle(HttpStatus.OK.value(), "", authenticationService.signIn(signinRequest)));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public String info() {
        return "개인정보";
    }

}
