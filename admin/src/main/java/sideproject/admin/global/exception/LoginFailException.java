package sideproject.admin.global.exception;

import sideproject.admin.common.enums.ErrorCode;

public class LoginFailException extends RuntimeException {
    private ErrorCode errorCode;

    public LoginFailException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
