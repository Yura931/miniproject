package sideproject.admin.global.exception;

import lombok.RequiredArgsConstructor;
import sideproject.admin.common.enums.ErrorCode;

@RequiredArgsConstructor
public class UserDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.USER_DUPLICATE;
}
