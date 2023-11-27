package subproject.admin.auth.principal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.global.exception.LoginFailException;
import subproject.admin.user.entity.Member;
import subproject.admin.user.repository.MemberRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 시큐리티 session(내부 Authentication(내부 User Details))
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return memberRepository.findOneWithRoleByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new LoginFailException("해당 유저 정보를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemberRole().toString());
        List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());

//        return new User(
//                member.getEmail(),
//                member.getPassword(),
//                grantedAuthorities
//        );

        return new PrincipalDetails(member, grantedAuthorities);
    }
}
