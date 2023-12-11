package subproject.admin.redis.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record RedisDto(
        String email,
        String token
) {
    public static RedisDto of(String email,  String token) {
        return new RedisDto(
                email,
                token
        );
    }
}
