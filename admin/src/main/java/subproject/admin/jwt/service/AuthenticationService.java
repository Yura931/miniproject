package subproject.admin.jwt.service;

import subproject.admin.jwt.dto.request.RefreshTokenRequest;
import subproject.admin.jwt.dto.request.SignUpRequest;
import subproject.admin.jwt.dto.request.SigninRequest;
import subproject.admin.jwt.dto.response.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.response.SignUpResponse;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SigninRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
