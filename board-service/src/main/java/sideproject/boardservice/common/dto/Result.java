package sideproject.boardservice.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Result<T> {

    private Integer status;
    private String msg;
    private T data;
    private String errorName;

    @Builder
    public Result(final Integer status, final String msg, final T data, final String errorName) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.errorName = errorName;
    }
}
