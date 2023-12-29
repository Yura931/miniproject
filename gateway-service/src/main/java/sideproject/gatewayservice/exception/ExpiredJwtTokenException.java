package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.gatewayservice.exception.enums.ErrorCode;


@Getter
@Slf4j
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExpiredJwtTokenException() {
        this.errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
    }

    public ExpiredJwtTokenException(ErrorCode errorCode) {
        log.info("ExpiredJwtTokenException errorCode : {}", errorCode);
        this.errorCode = errorCode;
    }
}
