package subproject.admin.global.exception;

import lombok.NoArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@NoArgsConstructor
public class ExpiredJwtTokenException extends RuntimeException {

    private ErrorCode errorCode;

    public ExpiredJwtTokenException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
