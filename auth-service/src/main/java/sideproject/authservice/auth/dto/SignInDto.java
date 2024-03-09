package sideproject.authservice.auth.dto;

import sideproject.authservice.auth.dto.request.SignInRequest;
import sideproject.authservice.user.enums.AccountType;

public record SignInDto (
        String email,
        String password,
        AccountType accountType
) {
    public static SignInDto from(SignInRequest request) {
        return new SignInDto(
                request.getEmail(),
                request.getPassword(),
                request.getAccountType()
        );
    }
}
