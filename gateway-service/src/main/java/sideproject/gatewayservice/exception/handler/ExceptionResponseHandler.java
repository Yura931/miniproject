package sideproject.gatewayservice.exception.handler;

import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import sideproject.gatewayservice.common.dto.Result;
import sideproject.gatewayservice.common.dto.ResultHandler;
import sideproject.gatewayservice.exception.*;
import sideproject.gatewayservice.exception.enums.ErrorCode;

@RestControllerAdvice @Slf4j
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public Result<?> handleInvalidTokenException(InvalidTokenException e) {
        return errorHandler(e, 401);
    }

    @ExceptionHandler(LoginFailException.class)
    public Result<?> handleLoginFailException(ErrorCode e) {
        return ResultHandler.errorHandle(e);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public Result<?> handleUserDuplicateException(ErrorCode e) {
        return ResultHandler.errorHandle(e);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public Result<?> handleUnsupportedJwtException(UnsupportedJwtException e) {
        return errorHandler(e, 401);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return errorHandler(e, 401);
    }
    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<Result> handleRefreshTokenInvalidException(ExpiredRefreshTokenException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(ResultHandler.errorHandle(e.getErrorCode()));
    }
    @ExceptionHandler(ExpiredJwtTokenException.class)
    public Result<?> handleExpiredJwtTokenException(ExpiredJwtTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }

    private Result<?> errorHandler(Exception e, Integer status) {
        log.error(e.getMessage());
        return Result.builder()
                .status(status)
                .msg(e.getMessage())
                .errorName(e.getClass().getSimpleName())
                .build();
    }

}
