package subproject.admin.global.exception;

import subproject.admin.common.enums.ErrorCode;

public class LoginFailException extends RuntimeException {
    private ErrorCode errorCode;

    public LoginFailException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
