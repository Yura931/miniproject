package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;


@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
