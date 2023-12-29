package sideproject.authservice.auth.dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import sideproject.authservice.auth.dto.request.SignUpRequest;
import sideproject.authservice.member.enums.AccountType;
import sideproject.authservice.member.enums.UserRoles;

public record SignUpDto (
        String email,
        String password,
        AccountType accountType,
        UserRoles userRoles
) {
    public static SignUpDto of(SignUpRequest request, PasswordEncoder passwordEncoder) {


        return new SignUpDto(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getAccountType(),
                request.getUserRoles()
        );
    }
}
