package subproject.admin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import subproject.admin.user.entity.RefreshToken;
import subproject.admin.user.repository.RefreshTokenRedisRepository;

import java.util.Optional;

@SpringBootTest
public class RedisTest {

    @Autowired
    RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisRepositoryTest() throws Exception {
        RefreshToken refreshToken = RefreshToken.builder().key("testKey").value("testValue").build();
        refreshTokenRedisRepository.save(refreshToken);
        RefreshToken token = refreshTokenRedisRepository.findById("testKey").get();
        Assertions.assertThat(refreshToken.getValue()).isEqualTo(token.getValue());
    }

    @Test
    public void redisTemplateTest() throws Exception {
        redisTemplate.opsForValue().set("testKey", "testValue");
        String testKey = redisTemplate.opsForValue().get("testKey");
        System.out.println("testKey = " + testKey);
    }
}
