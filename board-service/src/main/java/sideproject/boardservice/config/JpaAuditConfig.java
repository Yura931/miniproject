package sideproject.boardservice.config;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaAuditConfig {

    @Bean
    public AuditorAware<UUID> auditorAware() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return () -> Optional.ofNullable(authentication)
                .map(auth -> {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    return UUID.fromString(userDetails.getUsername());
                });
    }
}
