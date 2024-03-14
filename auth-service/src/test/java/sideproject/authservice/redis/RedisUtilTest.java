package sideproject.authservice.redis;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    final String KEY = "key";
    final String VALUE = "value";
    final Long MILLISECONDS = 5000L;

    @BeforeEach
    void setting() {
        redisUtil.set(KEY, VALUE, MILLISECONDS);
    }

    @Test
    @DisplayName("저장 데이터 조회")
    void setTest() {
        Optional<Object> optionalValue = redisUtil.get(KEY);
        optionalValue.ifPresent(value -> {
            assertThat(VALUE).isEqualTo(value);
        });
    }

    @Test
    @DisplayName("저장 데이터 수정")
    void updateTest() {
        String updateValue = "updateValue";
        redisUtil.set(KEY, updateValue, MILLISECONDS);

        Optional<Object> optionalValue = redisUtil.get(KEY);
        optionalValue.ifPresent(value -> {
            assertThat(updateValue).isEqualTo(value);
            assertThat(VALUE).isNotEqualTo(value);
        });
    }

    @Test
    @DisplayName("만료시간 테스트")
    void expiredTest() {

        // Awaitility

        Awaitility.await().pollDelay(Duration.ofMillis(6000)).untilAsserted(() -> {
            Optional<Object> atMostValue = redisUtil.get(KEY);
            assertThat(atMostValue).isEmpty();
        });

        /*Awaitility.await().atMost(Duration.ofMillis(6000)).untilAsserted(() -> {
            Optional<Object> atMostValue = redisUtil.get(KEY);
            assertThat(atMostValue).isEmpty();
        });*/
    }

}
