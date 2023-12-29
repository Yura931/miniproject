package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;
import sideproject.gatewayservice.redis.RedisUtil;


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
        redisUtil.delete(refreshToken);
    }
}
