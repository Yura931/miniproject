package sideproject.gatewayservice.exception.handler;

import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import sideproject.gatewayservice.common.dto.Result;
import sideproject.gatewayservice.common.dto.ResultHandler;
import sideproject.gatewayservice.exception.ExpiredJwtTokenException;
import sideproject.gatewayservice.exception.ExpiredRefreshTokenException;
import sideproject.gatewayservice.exception.InvalidTokenException;

@RestControllerAdvice @Slf4j
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public Result<?> handleInvalidTokenException(InvalidTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public Result<?> handleUnsupportedJwtException(InvalidTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(InvalidTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }
    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<Result> handleRefreshTokenInvalidException(ExpiredRefreshTokenException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(ResultHandler.errorHandle(e.getErrorCode()));
    }
    @ExceptionHandler(ExpiredJwtTokenException.class)
    public Result<?> handleExpiredJwtTokenException(ExpiredJwtTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }

}
