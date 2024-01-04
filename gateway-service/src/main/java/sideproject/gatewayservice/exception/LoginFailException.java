package sideproject.gatewayservice.exception;

import sideproject.gatewayservice.exception.enums.ErrorCode;

public class LoginFailException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.LOGIN_FAIL;
}
