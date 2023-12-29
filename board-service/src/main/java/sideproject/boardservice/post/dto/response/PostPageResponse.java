package sideproject.boardservice.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.post.dto.item.SelectPostItem;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPageResponse {
    private SelectPostItem items;
}
