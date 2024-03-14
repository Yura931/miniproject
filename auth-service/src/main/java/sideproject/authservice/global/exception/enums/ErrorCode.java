package sideproject.authservice.global.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    NOT_FOUND_HEADER_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 정보가 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "REFRESH TOKEN이 만료되었습니다."),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 가입되어 있는 유저입니다."),
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "이미 등록되어 있는 닉네임 입니다."),
    LOGIN_FAIL(HttpStatus.NOT_FOUND, "유저 정보가 존재하지 않습니다.");

    private HttpStatus status;
    private String message;
}
