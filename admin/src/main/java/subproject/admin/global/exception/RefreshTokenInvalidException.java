package subproject.admin.global.exception;

public class RefreshTokenInvalidException extends RuntimeException{

    private String message;
    public RefreshTokenInvalidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }

}
