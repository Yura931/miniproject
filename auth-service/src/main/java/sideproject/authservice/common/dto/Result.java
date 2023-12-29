package sideproject.authservice.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Result<T> {

    private Integer status;
    private String msg;
    private T data;
    private String errorName;

    private String code;



    @Builder
    public Result(final Integer status, final String msg, final T data, final String errorName, String code) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.errorName = errorName;
        this.code = code;
    }
}
