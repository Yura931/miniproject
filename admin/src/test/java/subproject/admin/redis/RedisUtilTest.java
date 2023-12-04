package subproject.admin.redis;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.annotation.Rollback;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;

    @BeforeEach
    public void beforeEach() {
        redisUtil.set("testKey", "testValue", 1000 * 60 * 60 * 24 * 7);
    }

    @Test
    public void redisUtilSetTest() throws Exception {
        String testValue = Objects.toString(redisUtil.get("testKey"));
        assertThat("testValue").isEqualTo(testValue);
    }

    @Test
    public void redisExpireTest() throws Exception {
        Long expireInSeconds = redisUtil.getRefreshTokenExpire("testKey");
        Duration duration = Duration.ofSeconds(expireInSeconds);
        Map<String, Long> map = durationConv(duration);
        long hours = map.get("days");

        assertThat(hours).isEqualTo(7);
    }

    @Test
    public void redisMultiDelete() throws Exception {
        redisUtil.multiDelete(Arrays.asList("testKey", "admin@gmail.com", "refresh_token"));
    }

    private Map<String, Long> durationConv(Duration duration) {
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();

        Map<String, Long> map = new HashMap<>();
        map.put("days", days);
        map.put("hours", hours);
        map.put("minutes", minutes);
        return map;
    }


}