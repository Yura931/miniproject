package sideproject.boardservice.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.post.dto.item.SearchPostItem;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostResponse {
    private SearchPostItem items;
}
