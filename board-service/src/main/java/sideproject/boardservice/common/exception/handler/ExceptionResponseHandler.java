package sideproject.boardservice.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.handler.advice.RequestHandlerCircuitBreakerAdvice;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sideproject.boardservice.common.dto.Result;
import sideproject.boardservice.common.dto.ResultHandler;
import sideproject.boardservice.common.exception.ExpiredJwtTokenException;
import sideproject.boardservice.common.exception.UserInformationNotMatchException;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpiredJwtTokenException.class)
    public Result<?> handleExpiredJwtTokenException(ExpiredJwtTokenException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }

    @ExceptionHandler(UserInformationNotMatchException.class)
    public Result<?> handleUserInformationNotMatchException(UserInformationNotMatchException e) {
        return ResultHandler.errorHandle(e.getErrorCode());
    }

    @ExceptionHandler(RequestHandlerCircuitBreakerAdvice.CircuitBreakerOpenException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String> handleCircuitBreakerOpenException(RequestHandlerCircuitBreakerAdvice.CircuitBreakerOpenException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.toString());
    }

    @ExceptionHandler(NoFallbackAvailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<Throwable> handleNoFallbackAvailableException(HttpServletRequest request, NoFallbackAvailableException ex) {
        log.warn("uri -> {}, exception -> {}", request.getRequestURI(), ex.getCause());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getCause());
    }

    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String> handleUnknownHostException(UnknownHostException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.toString());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResultHandler.validErrorHandle(errors, HttpStatus.BAD_REQUEST.value())
        );
    }
}
