package subproject.admin.auth.principal;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import subproject.admin.user.entity.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return null;
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
        return true;
    }
}
