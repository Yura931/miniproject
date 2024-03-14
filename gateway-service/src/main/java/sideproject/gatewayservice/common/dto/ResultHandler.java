package sideproject.gatewayservice.common.dto;

import sideproject.gatewayservice.exception.enums.ErrorCode;

public class ResultHandler {

    public static Result<?> handle(final Integer status, final String msg, final Object data) {
        return Result.builder().status(status).msg(msg).data(data).build();
    }

    public static Result<?> errorHandle(final Integer status, final String msg) {
        return Result.builder()
                .status(status)
                .msg(msg).build();
    }

    public static Result<?> errorHandle(ErrorCode e) {
        return Result.builder()
                .status(e.getStatus().value())
                .msg(e.getMessage())
                .data(e)
                .errorName(e.name())
                .build();
    }

}
