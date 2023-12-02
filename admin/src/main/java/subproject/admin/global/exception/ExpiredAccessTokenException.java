package subproject.admin.global.exception;

import lombok.NoArgsConstructor;
import subproject.admin.common.enums.ErrorCode;

@NoArgsConstructor
public class ExpiredAccessTokenException extends RuntimeException {
    private ErrorCode errorCode;

    public ExpiredAccessTokenException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
