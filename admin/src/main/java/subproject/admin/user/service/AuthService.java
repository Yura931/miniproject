package subproject.admin.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.global.exception.UserDuplicateException;
import subproject.admin.jwt.TokenProvider;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.user.dto.MemberDto;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.RefreshToken;
import subproject.admin.user.repository.MemberRepository;
import subproject.admin.user.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberDto.MemberResponseDto signup(MemberDto.MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Member member = Member.joinNewAdminMember(
                memberRequestDto.getEmail(),
                passwordEncoder.encode(memberRequestDto.getPassword())
        );
        Member saveMember = memberRepository.save(member);
        return MemberDto.MemberResponseDto.of(saveMember);
    }

    @Transactional
    public TokenDto login(MemberDto.MemberRequestDto memberRequestDto) {
        // login ID/PW를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 실제로 검증(사용자 비밀번호 체크)
        // authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만들었던 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
