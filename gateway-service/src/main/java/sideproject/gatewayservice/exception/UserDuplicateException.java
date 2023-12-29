package sideproject.gatewayservice.exception;

import lombok.RequiredArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@RequiredArgsConstructor
public class UserDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.USER_DUPLICATE;
}
