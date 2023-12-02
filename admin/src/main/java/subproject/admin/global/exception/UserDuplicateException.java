package subproject.admin.global.exception;

import subproject.admin.common.enums.ErrorCode;

public class UserDuplicateException extends RuntimeException {
    private ErrorCode errorCode;

    public UserDuplicateException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
