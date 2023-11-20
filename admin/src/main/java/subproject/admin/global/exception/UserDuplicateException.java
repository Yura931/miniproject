package subproject.admin.global.exception;

public class UserDuplicateException extends RuntimeException {
    private String message;

    public UserDuplicateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
