package subproject.admin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // logout token 요청 exception

    // access token, refresh token 만료 exception

    // header token not found exception

    LOGOUT_TOKEN(410, "유효하지 않은 토큰입니다.", Code.C001),
    EXPIRED_JWT_TOKEN(411, "토큰이 만료되었습니다.", Code.C001),
    HEADER_TOKEN_NOT_FOUND(412, "토큰 정보가 없습니다.", Code.C001),
    INVALID_TOKEN(413, "유효하지 않은 토큰입니다.", Code.C001),
    EXPIRED_REFRESH_TOKEN(414, "REFRESH TOKEN이 만료되었습니다.", Code.C001),
    EXPIRED_ACCESS_TOKEN(415, "ACCESS TOKEN이 만료되었습니다.", Code.C001 ),
    USER_DUPLICATE(420, "이미 가입되어있는 유저입니다." ,Code.C001),
    LOGIN_FAIL(421,"유저 정보가 존재하지 않습니다." , Code.C001);


    private Integer status;
    private String message;
    private Code code;

    public enum Code {
        C001,
    }
}
