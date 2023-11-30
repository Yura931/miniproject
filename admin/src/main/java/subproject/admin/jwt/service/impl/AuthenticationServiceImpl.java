package subproject.admin.jwt.service.impl;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import subproject.admin.global.exception.LoginFailException;
import subproject.admin.global.exception.UserDuplicateException;
import subproject.admin.jwt.dto.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.SignUpRequest;
import subproject.admin.jwt.dto.SignUpResponse;
import subproject.admin.jwt.dto.SigninRequest;
import subproject.admin.jwt.service.AuthenticationService;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.jwt.service.UserService;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;
import subproject.admin.user.entity.RefreshToken;
import subproject.admin.user.repository.MemberRepository;
import subproject.admin.user.repository.RefreshTokenRepository;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;


    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if(memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Member member = Member.joinNewAdminMember(
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        MemberRole.generateNewMemberByRoleAdmin(member);
        Member saveMember = memberRepository.save(member);
        return new SignUpResponse(saveMember.getEmail());
    }

    public JwtAuthenticationResponse signIn(SigninRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                        signInRequest.getPassword()));

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signInRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        final String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userDetails);

        refreshTokenRepository.save(RefreshToken.builder()
                        .key(userDetails.getUsername())
                        .value(refreshToken)
                        .build());
        return JwtAuthenticationResponse.of(token, refreshToken);
    }

}
