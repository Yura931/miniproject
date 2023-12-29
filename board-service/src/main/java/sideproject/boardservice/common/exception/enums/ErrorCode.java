package sideproject.boardservice.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXPIRED_JWT_TOKEN(481, "토큰이 만료되었습니다.");

    private Integer status;
    private String message;
}
