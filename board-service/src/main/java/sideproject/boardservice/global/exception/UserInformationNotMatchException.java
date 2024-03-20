package sideproject.boardservice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.global.exception.enums.ErrorCode;

@RequiredArgsConstructor
@Getter
public class UserInformationNotMatchException extends RuntimeException{
    private final ErrorCode errorCode = ErrorCode.USER_INFO_NOT_MATCH;
}
