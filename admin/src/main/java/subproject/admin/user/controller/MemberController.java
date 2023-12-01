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
import subproject.admin.jwt.dto.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.RefreshTokenRequest;
import subproject.admin.jwt.dto.SignUpRequest;
import subproject.admin.jwt.dto.SigninRequest;
import subproject.admin.jwt.properties.JwtProperties;
import subproject.admin.jwt.service.AuthenticationService;

import java.security.Principal;

import static subproject.admin.jwt.properties.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class MemberController {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public String info() {
        return "개인정보";
    }

}
