package subproject.admin.jwt.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenRequest {
    private String token;
}
