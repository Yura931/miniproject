package sideproject.admin.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.admin.common.enums.ErrorCode;


@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
