package subproject.admin.post.dto.response;

import lombok.RequiredArgsConstructor;
import subproject.admin.post.dto.item.PostItem;

@RequiredArgsConstructor
public class SearchPostResponse {
    private final PostItem postItem;
}
