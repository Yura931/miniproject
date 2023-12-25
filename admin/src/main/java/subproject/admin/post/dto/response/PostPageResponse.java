package subproject.admin.post.dto.response;

import lombok.*;
import subproject.admin.post.dto.item.SelectPostItem;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPageResponse {
    private SelectPostItem items;
}
