package sideproject.authservice.global.exception;

public class NotFoundTokenFromHeaderException extends RuntimeException {

    private String message;

    public NotFoundTokenFromHeaderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
