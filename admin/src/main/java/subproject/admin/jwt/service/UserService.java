package subproject.admin.jwt.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public UserDetailsService userDetailsService();
}
