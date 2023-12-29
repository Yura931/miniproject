package sideproject.authservice.global.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // logout token 요청 exception

    // access token, refresh token 만료 exception

    // header token not found exception

    LOGOUT_TOKEN(480, "유효하지 않은 토큰입니다."),
    EXPIRED_JWT_TOKEN(481, "토큰이 만료되었습니다."),
    HEADER_TOKEN_NOT_FOUND(482, "토큰 정보가 없습니다."),
    INVALID_TOKEN(483, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(484, "REFRESH TOKEN이 만료되었습니다."),
    USER_DUPLICATE(420, "이미 가입되어있는 유저입니다."),
    LOGIN_FAIL(421,"유저 정보가 존재하지 않습니다.");

    private Integer status;
    private String message;
}
