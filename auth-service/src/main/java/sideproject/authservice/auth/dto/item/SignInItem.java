package sideproject.authservice.auth.dto.item;

public record SignInItem (
        String token
) {
    public static SignInItem tokenItem(String token) {
        return new SignInItem(token);
    }
}
