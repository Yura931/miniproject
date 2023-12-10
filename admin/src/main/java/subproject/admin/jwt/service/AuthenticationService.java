package subproject.admin.jwt.service;

import jakarta.servlet.http.HttpServletResponse;
import subproject.admin.jwt.dto.RefreshTokenDto;
import subproject.admin.jwt.dto.request.SignUpRequest;
import subproject.admin.jwt.dto.request.SigninRequest;
import subproject.admin.jwt.dto.response.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.response.SignUpResponse;
import subproject.admin.jwt.principal.PrincipalDetails;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SigninRequest signInRequest, HttpServletResponse response);
    JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenRequest, HttpServletResponse response);
}
