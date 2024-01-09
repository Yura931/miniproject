package sideproject.authservice.global.exception;

import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
public class NicknameDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.NICKNAME_DUPLICATE;
}
