package subproject.admin.global.exception;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@RequiredArgsConstructor
public class ExpiredRefreshTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_REFRESH_TOKEN;
}
