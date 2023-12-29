package sideproject.authservice.redis.dto;

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
