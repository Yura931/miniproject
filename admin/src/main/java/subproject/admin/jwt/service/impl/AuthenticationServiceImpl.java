package subproject.admin.jwt.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import subproject.admin.common.enums.ErrorCode;
import subproject.admin.common.util.CookieUtil;
import subproject.admin.global.exception.ExpiredJwtTokenException;
import subproject.admin.global.exception.ExpiredRefreshTokenException;
import subproject.admin.global.exception.UserDuplicateException;
import subproject.admin.jwt.dto.RefreshTokenDto;
import subproject.admin.jwt.dto.request.SignUpRequest;
import subproject.admin.jwt.dto.request.SigninRequest;
import subproject.admin.jwt.dto.response.JwtAuthenticationResponse;
import subproject.admin.jwt.dto.response.SignUpResponse;
import subproject.admin.jwt.principal.PrincipalDetails;
import subproject.admin.jwt.service.AuthenticationService;
import subproject.admin.jwt.service.JWTService;
import subproject.admin.jwt.service.UserService;
import subproject.admin.user.entity.Member;
import subproject.admin.user.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static subproject.admin.jwt.properties.JwtProperties.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieUtil cookieUtil;


    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if(memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserDuplicateException(ErrorCode.USER_DUPLICATE);
        }

        Member member = Member.joinNewAdminMember(
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        Member saveMember = memberRepository.save(member);
        return new SignUpResponse(saveMember.getEmail());
    }

    public JwtAuthenticationResponse signIn(SigninRequest signInRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                        signInRequest.getPassword()));

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signInRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        final String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userDetails);
        cookieUtil.addCookie(response, REFRESH_PREFIX, refreshToken);

        return JwtAuthenticationResponse.from(token);
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenRequest, HttpServletResponse response) {
        String userEmail = "";
        try {
            userEmail = jwtService.extractUserName(refreshTokenRequest.refreshToken());
        } catch (ExpiredJwtTokenException e) {
            throw new ExpiredRefreshTokenException();
        } finally {

            Member member = memberRepository.findOneWithRoleByEmail(userEmail)
                    .orElseThrow(EntityNotFoundException::new);

            List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                    .collect(Collectors.toList());

            UserDetails userDetails = new PrincipalDetails(member, grantedAuthorities);

            String token = jwtService.generateToken(userDetails);
            cookieUtil.addCookie(response, REFRESH_PREFIX, refreshTokenRequest.refreshToken());
            return JwtAuthenticationResponse.from(token);
        }
    }

}
