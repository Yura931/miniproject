package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class LoginFailException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.LOGIN_FAIL;
}
