package subproject.admin.jwt.service;

import subproject.admin.jwt.dto.*;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SigninRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
