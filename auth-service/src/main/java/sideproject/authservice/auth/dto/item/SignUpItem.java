package sideproject.authservice.auth.dto.item;


import sideproject.authservice.member.entity.Users;
import sideproject.authservice.member.enums.AccountType;

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
