package sideproject.authservice.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.authservice.member.enums.AccountType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    private AccountType accountType;
}
