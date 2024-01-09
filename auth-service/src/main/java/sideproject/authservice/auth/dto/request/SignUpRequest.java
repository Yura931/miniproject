package sideproject.authservice.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.authservice.member.enums.AccountType;
import sideproject.authservice.member.enums.UserRoles;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private AccountType accountType;

    @NotBlank
    private UserRoles userRoles;
}
