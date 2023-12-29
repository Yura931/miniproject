package sideproject.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sideproject.authservice.principal.PrincipalDetails;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return () -> Optional.ofNullable(authentication)
                .map((auth) -> {
                    PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
                    return userDetails.getUsername();
                })
                .orElse("admin").describeConstable();
    }
}
