package sideproject.boardservice.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_INFO_NOT_MATCH(480, "유저 정보가 일치하지 않습니다."),
    EXPIRED_JWT_TOKEN(481, "토큰이 만료되었습니다.");


    private Integer status;
    private String message;
}
