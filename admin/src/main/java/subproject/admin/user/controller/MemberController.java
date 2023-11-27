package subproject.admin.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.TokenProvider;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.user.dto.MemberDto;
import subproject.admin.user.service.AuthService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class MemberController {
    private final AuthService authService;

    // 커스텀 Result, ResponseEntity 뭐가 더 좋은 방법??
    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody MemberDto.MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "", authService.signup(memberRequestDto)));
    }

    @PostMapping("/login")
    public ResponseEntity role(@Valid @RequestBody MemberDto.MemberRequestDto loginDto) {
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "", authService.login(loginDto)));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public String info() {
        return "개인정보";
    }

}
