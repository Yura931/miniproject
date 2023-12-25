package subproject.admin.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.post.dto.item.SelectPostItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelectPostResponse {
    private SelectPostItem items;
}
