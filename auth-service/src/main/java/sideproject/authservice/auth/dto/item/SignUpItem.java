package sideproject.authservice.auth.dto.item;


import sideproject.authservice.user.entity.Users;
import sideproject.authservice.user.enums.AccountType;

public record SignUpItem (
    String email,
    AccountType accountType
) {
    public static SignUpItem UserEntityToDto(Users users) {
        return new SignUpItem(
                users.getEmail(),
                users.getAccountType()
        );
    }
}
