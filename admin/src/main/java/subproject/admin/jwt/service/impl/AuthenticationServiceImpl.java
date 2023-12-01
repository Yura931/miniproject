package subproject.admin.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import subproject.admin.global.exception.UserDuplicateException;
import subproject.admin.jwt.dto.*;
import subproject.admin.jwt.principal.PrincipalDetails;
import subproject.admin.jwt.service.AuthenticationService;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.jwt.service.UserService;
import subproject.admin.user.entity.Member;
import subproject.admin.user.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if(memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Member member = Member.joinNewAdminMember(
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
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

        return JwtAuthenticationResponse.of(token, refreshToken);
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Member member = memberRepository.findOneWithRoleByEmail(userEmail).orElseThrow();

        List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());

        UserDetails userDetails = new PrincipalDetails(member, grantedAuthorities);

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), userDetails)) {
            var jwt = jwtService.generateToken(userDetails);
            return JwtAuthenticationResponse.of(jwt, refreshTokenRequest.getToken());
        }

        return null;
    }
}
