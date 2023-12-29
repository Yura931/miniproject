package sideproject.authservice.auth.dto;

import sideproject.authservice.auth.dto.request.SignUpRequest;
import sideproject.authservice.member.enums.AccountType;

public record SignInDto (
        String email,
        String password,
        AccountType accountType
) {
    public static SignInDto from(SignUpRequest request) {
        return new SignInDto(
                request.getEmail(),
                request.getPassword(),
                request.getAccountType()
        );
    }
}
