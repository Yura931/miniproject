package subproject.admin.jwt.principal;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 로그인 진행이 완료되면 시큐리티 session을 만들어 줌(시큐리티가 자신만의 세션 공간을 가짐, 키 값으로 구분, Security ContextHolder)
 * 시큐리티가 가지고 있는 세션에 들어갈 수 있는 오브젝트가 정해져 있음 -> Authentication 타입 객체
 * Authentication 안에 User정보가 있어야 됨
 * User 오브젝트타입 -> UserDetails 타입 객체
 *
 * Security Session => Authentication => UserDetails(PrincipalDetails)
 */
@Getter
public class PrincipalDetails implements UserDetails {

    private Member member;
    private Collection<GrantedAuthority> authorities;
    private Map<String, Object> memberInfoMap;

    public PrincipalDetails(Member user, Collection<GrantedAuthority> authorities) {
        this.member = user;
        this.authorities = authorities;
    }

    public PrincipalDetails(Member user, Map<String, Object> memberInfoMap) {
        this.member = user;
        this.memberInfoMap = memberInfoMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemberRole().toString());
//        return Collections.singleton(grantedAuthority);

//        return member.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
//                .collect(Collectors.toList());
        return authorities;
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 사이트에서 1년동안 회원이 로그인을 안하면 휴먼 계정으로 하기로 함
        return true;
    }
}
