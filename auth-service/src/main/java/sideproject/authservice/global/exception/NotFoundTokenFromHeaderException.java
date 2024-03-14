package sideproject.authservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.authservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class NotFoundTokenFromHeaderException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.NOT_FOUND_HEADER_TOKEN;
}
