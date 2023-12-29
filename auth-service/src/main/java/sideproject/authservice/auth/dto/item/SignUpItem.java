package sideproject.authservice.auth.dto.item;


import sideproject.authservice.member.entity.Member;
import sideproject.authservice.member.enums.AccountType;

public record SignUpItem (
    String email,
    AccountType accountType
) {
    public static SignUpItem UserEntityToDto(Member member) {
        return new SignUpItem(
                member.getEmail(),
                member.getAccountType()
        );
    }
}
