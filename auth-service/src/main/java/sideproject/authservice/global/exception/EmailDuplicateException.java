package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class EmailDuplicateException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EMAIL_DUPLICATE;
}
