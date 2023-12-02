package subproject.admin.global.exception;

import lombok.NoArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@NoArgsConstructor
public class ExpiredRefreshTokenException extends RuntimeException {

    private ErrorCode errorCode;

    public ExpiredRefreshTokenException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
