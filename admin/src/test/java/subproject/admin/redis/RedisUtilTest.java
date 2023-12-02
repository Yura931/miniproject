package subproject.admin.redis;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.annotation.Rollback;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void redisUtilSetTest() throws Exception {
        redisUtil.set("testKey", "testValue", 10);
        String testValue = Objects.toString(redisUtil.get("testKey"));
        Assertions.assertThat("testValue").isEqualTo(testValue);
    }

    @Test
    public void redisExpireTest() throws Exception {
        Long expireInSeconds = redisUtil.getRefreshTokenExpire("testKey");
        Duration duration = Duration.ofSeconds(expireInSeconds);
        String durationDate = durationConv(duration);
        System.out.println("durationDate = " + durationDate);
    }

    @Test
    public void redisMultiDelete() throws Exception {
        redisUtil.multiDelete(Arrays.asList("testKey", "admin@gmail.com", "refresh_token"));
    }

    private String durationConv(Duration duration) {
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();

        return StringUtils.join("남은 시간: ", days, "일 ", hours, "시간 ", minutes, "분");
    }


}