package subproject.admin.post.dto.response;

import lombok.*;
import subproject.admin.post.dto.item.PostItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostResponse {
    private PostItem items;
}
