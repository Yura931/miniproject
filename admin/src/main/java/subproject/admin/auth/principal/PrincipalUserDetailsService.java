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

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findOneWithRoleByEmail(username)
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
