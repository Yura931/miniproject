package sideproject.authservice.global.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
    private String message;

    public InvalidTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
