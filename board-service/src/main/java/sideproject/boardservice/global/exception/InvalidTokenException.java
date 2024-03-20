package sideproject.boardservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class InvalidTokenException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
}
