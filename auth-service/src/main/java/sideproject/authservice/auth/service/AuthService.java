package sideproject.authservice.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sideproject.authservice.auth.dto.RefreshTokenDto;
import sideproject.authservice.auth.dto.SignInDto;
import sideproject.authservice.auth.dto.SignUpDto;
import sideproject.authservice.auth.dto.response.LogoutResponse;
import sideproject.authservice.auth.dto.response.ReIssueAccessTokenResponse;
import sideproject.authservice.auth.dto.response.SignInResponse;
import sideproject.authservice.auth.dto.response.SignUpResponse;

public interface AuthService {
    SignInResponse signIn(SignInDto dto, HttpServletResponse response);
    SignUpResponse signUp(SignUpDto dto);
    LogoutResponse logout(HttpServletRequest request, HttpServletResponse response);
    ReIssueAccessTokenResponse reIssueAccessToken(RefreshTokenDto dto, HttpServletResponse response);
}
