package sideproject.boardservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.common.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
