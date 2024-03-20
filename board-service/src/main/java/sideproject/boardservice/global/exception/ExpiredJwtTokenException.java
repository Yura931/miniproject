package sideproject.boardservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
