package sideproject.authservice.global.exception;

import sideproject.authservice.global.exception.enums.ErrorCode;

public class LoginFailException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.LOGIN_FAIL;
}
