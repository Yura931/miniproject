package subproject.admin.post.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import subproject.admin.post.dto.item.PostItem;

@RequiredArgsConstructor
public class RegisterPostResponse {
    private final PostItem postItem;
}
