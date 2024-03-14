package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class LogoutTokenRequestException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.INVALID_TOKEN;

}
