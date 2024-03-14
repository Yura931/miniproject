package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class NicknameDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.NICKNAME_DUPLICATE;
}
