package subproject.admin.jwt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @NotNull
    @NotBlank(message = "email을 입력해주세요.")
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @NotBlank(message = "password를 입력해주세요.")
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public SignUpRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
