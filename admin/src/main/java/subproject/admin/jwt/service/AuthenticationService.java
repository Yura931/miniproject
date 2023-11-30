package subproject.admin.jwt.service;

import subproject.admin.jwt.dto.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.SignUpRequest;
import subproject.admin.jwt.dto.SignUpResponse;
import subproject.admin.jwt.dto.SigninRequest;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SigninRequest signInRequest);
}
