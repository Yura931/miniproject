package sideproject.boardservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class NotFoundTokenFromHeaderException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.NOT_FOUND_HEADER_TOKEN;
}
