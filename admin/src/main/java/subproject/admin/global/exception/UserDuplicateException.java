package subproject.admin.global.exception;

import lombok.RequiredArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@RequiredArgsConstructor
public class UserDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.USER_DUPLICATE;
}
