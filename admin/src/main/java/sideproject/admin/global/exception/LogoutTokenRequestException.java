package sideproject.admin.global.exception;

import lombok.RequiredArgsConstructor;
import sideproject.admin.common.enums.ErrorCode;

@RequiredArgsConstructor
public class LogoutTokenRequestException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.LOGOUT_TOKEN;

}
