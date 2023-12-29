package sideproject.authservice.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import sideproject.authservice.member.enums.AccountType;
import sideproject.authservice.member.enums.UserRoles;

@Getter
@AllArgsConstructor
@ToString
public class SignUpRequest {
    private String email;
    private String password;
    private AccountType accountType;
    private UserRoles userRoles;
}
