package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@Getter
@RequiredArgsConstructor
public class InvalidTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
}
