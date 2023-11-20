package subproject.admin.global.exception;

public class LoginFailException extends RuntimeException {
    private String message;

    public LoginFailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
