package sideproject.boardservice.common.dto;

import sideproject.boardservice.common.exception.enums.ErrorCode;

import java.util.Map;

public class ResultHandler {

    public static Result<?> handle(final Integer status, final String msg) {
        return Result.builder().status(status).msg(msg).build();
    }

    public static Result<?> handle(final Integer status, final String msg, final Object data) {
        return Result.builder().status(status).msg(msg).data(data).build();
    }

    public static Result<?> errorHandle(ErrorCode e) {
        return Result.builder()
                .status(e.getStatus())
                .msg(e.getMessage())
                .data(e)
                .errorName(e.name())
                .build();
    }

    public static Result<?> validErrorHandle(Map<String, String> errors, Integer status) {
        return Result.builder()
                .status(status)
                .msg("valid error")
                .data(errors)
                .build();
    }

}
