package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;
import sideproject.authservice.jwt.properties.JwtProperties;
import sideproject.authservice.redis.RedisUtil;

import static sideproject.authservice.jwt.properties.JwtProperties.*;


@NoArgsConstructor
@Getter
public class ExpiredRefreshTokenException extends RuntimeException {
    // refresh token 만료
    // redis 저장되어 있는 token 삭제

    private ErrorCode errorCode = ErrorCode.EXPIRED_REFRESH_TOKEN;
    private RedisUtil redisUtil;
    public ExpiredRefreshTokenException(RedisUtil redisUtil, String refreshToken) {
        this.redisUtil = redisUtil;
        refreshTokenDelete(refreshToken);
    }

    private void refreshTokenDelete(String refreshToken) {
        redisUtil.delete(REFRESH_TOKEN_KEY_PREFIX.concat(refreshToken));
    }
}
