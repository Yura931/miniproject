package subproject.admin.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import subproject.admin.common.enums.ErrorCode;


@RequiredArgsConstructor
@Getter
public class ExpiredJwtTokenException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.EXPIRED_JWT_TOKEN;
}
