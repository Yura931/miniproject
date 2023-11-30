package subproject.admin.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import subproject.admin.jwt.principal.PrincipalDetails;
import subproject.admin.global.exception.LoginFailException;
import subproject.admin.jwt.service.UserService;
import subproject.admin.user.entity.Member;
import subproject.admin.user.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MemberRepository memberRepository;

    public UserDetailsService userDetailsService() {
        // loadUserByUsername()
        return username -> (UserDetails) memberRepository.findOneWithRoleByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new LoginFailException("해당 유저 정보를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());
        return new PrincipalDetails(member, grantedAuthorities);
    }

}
