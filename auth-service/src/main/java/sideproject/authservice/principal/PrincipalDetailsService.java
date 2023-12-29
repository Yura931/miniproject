package sideproject.authservice.principal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sideproject.authservice.auth.repository.AuthRepository;
import sideproject.authservice.global.exception.LoginFailException;

@Component
@RequiredArgsConstructor
public class PrincipalDetailsService {

    private final AuthRepository authRepository;

    public UserDetailsService userDetailsService() {
        return username -> authRepository.findOneWithRoleByEmail(username)
                .map(PrincipalDetails::new)
                .orElseThrow(() -> new LoginFailException());
    }

}
