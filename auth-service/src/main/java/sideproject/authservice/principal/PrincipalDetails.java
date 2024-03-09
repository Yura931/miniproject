package sideproject.authservice.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sideproject.authservice.user.entity.Users;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    private Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.users.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
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

    public UUID getId() {
        return this.users.getId();
    }

    public String getNickname() {
        return this.users.getNickname();
    }

    public Users getUsers() {
        return this.users;
    }
}
