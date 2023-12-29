package sideproject.gatewayservice.exception;

import lombok.RequiredArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@RequiredArgsConstructor
public class LogoutTokenRequestException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.LOGOUT_TOKEN;

}
