package sideproject.authservice.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sideproject.authservice.auth.dto.item.SignInItem;

@Getter
@AllArgsConstructor
public class SignInResponse {
    private SignInItem items;
}
