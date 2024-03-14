package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.gatewayservice.exception.enums.ErrorCode;


@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
