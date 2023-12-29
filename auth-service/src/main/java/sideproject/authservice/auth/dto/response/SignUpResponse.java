package sideproject.authservice.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.authservice.auth.dto.item.SignUpItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpResponse {
    private SignUpItem items;
}
