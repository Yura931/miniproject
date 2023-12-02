package subproject.admin.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "email", "password" })
public class SigninRequest {

    @NotNull
    @NotBlank(message = "email을 입력해주세요.")
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @NotBlank(message = "password를 입력해주세요.")
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public SigninRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
