package sideproject.authservice.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be equal or grater than eight characters")
    private String password;

    @NotBlank(message = "Nickname cannot be blank")
    private String nickname;

    @NotNull
    private AccountType accountType;


    private UserRoles userRoles;
}
