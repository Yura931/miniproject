package sideproject.admin.jwt.principal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sideproject.admin.common.enums.ErrorCode;
import sideproject.admin.global.exception.LoginFailException;
import sideproject.admin.user.entity.Member;
import sideproject.admin.user.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrincipalDetailsService {
    private final MemberRepository memberRepository;

    public UserDetailsService userDetailsService() {
        // loadUserByUsername()
        return username -> memberRepository.findOneWithRoleByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new LoginFailException(ErrorCode.LOGIN_FAIL));
    }

    private PrincipalDetails createUserDetails(Member member) {
        List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());
        return new PrincipalDetails(member, grantedAuthorities);
    }
}
