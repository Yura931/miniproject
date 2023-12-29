package sideproject.authservice.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> redisBlackListTemplate;

    public void set(String key, Object o, long milliseconds) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisTemplate.opsForValue().set(
                getRefreshTokenKey(key), o, milliseconds, TimeUnit.MILLISECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(getRefreshTokenKey(key));
    }

    public Long getAccessTokenExpire(String key) {
        return redisTemplate.getExpire(getAccessTokenKey(key));
    }

    public Long getRefreshTokenExpire(String key) {
        return redisTemplate.getExpire(getRefreshTokenKey(key));
    }

    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(getRefreshTokenKey(key)));
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getRefreshTokenKey(key)));
    }

    public void setBlackList(String key, Object o, int minutes) {
        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisBlackListTemplate.opsForValue().set(
                getAccessTokenKey(key), o, minutes, TimeUnit.MINUTES);
    }

    public Object getBlackList(String key) {
        return redisBlackListTemplate.opsForValue().get(getAccessTokenKey(key));
    }

    public boolean deleteBlackList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.delete(getAccessTokenKey(key)));
    }

    public boolean hasKeyBlackList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(getAccessTokenKey(key)));
    }

    public void multiDelete(Collection<String> keyList) {
        keyList.stream()
                        .forEach(e -> {
                            redisTemplate.delete(e);
                            redisTemplate.delete(getAccessTokenKey(e));
                            redisTemplate.delete(getRefreshTokenKey(e));
                        });
    }

    private String getAccessTokenKey(String userEmail) {
        return "access_token:" + userEmail;
    }

    private String getRefreshTokenKey(String userEmail) {
        return "refresh_token:" + userEmail;
    }

}
