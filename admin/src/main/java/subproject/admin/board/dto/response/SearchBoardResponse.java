package subproject.admin.board.dto.response;

import lombok.RequiredArgsConstructor;
import subproject.admin.board.dto.item.BoardItem;

@RequiredArgsConstructor
public class SearchBoardResponse {
    private final BoardItem items;
}
