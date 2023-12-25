package subproject.admin.post.dto.response;

import lombok.*;
import subproject.admin.post.dto.item.RegisterPostItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostResponse {
    private RegisterPostItem items;
}
