package subproject.admin.global.exception;

import subproject.admin.common.enums.ErrorCode;

public class LogoutTokenRequestException extends RuntimeException {

    ErrorCode errorCode;

    public LogoutTokenRequestException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
