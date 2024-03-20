package sideproject.boardservice.global.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_INFO_NOT_MATCH(HttpStatus.CONFLICT, "유저 정보가 일치하지 않습니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    NOT_FOUND_HEADER_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 정보가 없습니다.");


    private HttpStatus status;
    private String message;
}
