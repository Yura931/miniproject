package sideproject.gatewayservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class NotFoundTokenFromHeaderException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.NOT_FOUND_HEADER_TOKEN;
}
