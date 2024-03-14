package sideproject.authservice.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> redisBlackListTemplate;

    public void set(String key, Object o, long milliseconds) {
        redisTemplate.opsForValue().set(key, o, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Transactional(readOnly = true)
    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public Long getAccessTokenExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public Long getRefreshTokenExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setBlackList(String key, Object o, int minutes) {
        redisBlackListTemplate.opsForValue().set(
                key, o, minutes, TimeUnit.MINUTES);
    }

    @Transactional(readOnly = true)
    public Optional<Object> getBlackList(String key) {
        return Optional.ofNullable(redisBlackListTemplate.opsForValue().get(key));
    }

    public boolean deleteBlackList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.delete(key));
    }

    public boolean hasKeyBlackList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(key));
    }

    public void multiDelete(Collection<String> keyList) {
        keyList.stream()
                        .forEach(e -> {
                            redisTemplate.delete(e);
                            redisTemplate.delete(e);
                            redisTemplate.delete(e);
                        });
    }


}
