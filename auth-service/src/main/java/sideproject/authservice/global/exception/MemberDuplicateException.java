package sideproject.authservice.global.exception;

import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
public class MemberDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.USER_DUPLICATE;
}
