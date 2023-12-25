package subproject.admin.post.dto.response;

import lombok.*;
import subproject.admin.post.dto.item.SearchPostItem;
import subproject.admin.post.dto.item.SelectPostItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostResponse {
    private SearchPostItem items;
}
