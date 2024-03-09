package sideproject.authservice.auth.dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import sideproject.authservice.auth.dto.request.SignUpRequest;
import sideproject.authservice.user.enums.AccountType;

public record SignUpDto (
        String email,
        String password,
        String nickname,
        AccountType accountType
) {
    public static SignUpDto of(SignUpRequest request, PasswordEncoder passwordEncoder) {

        return new SignUpDto(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getNickname(),
                request.getAccountType()
        );
    }
}
