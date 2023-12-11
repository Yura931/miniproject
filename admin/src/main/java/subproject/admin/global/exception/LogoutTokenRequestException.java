package subproject.admin.global.exception;

import lombok.RequiredArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@RequiredArgsConstructor
public class LogoutTokenRequestException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.LOGOUT_TOKEN;

}
