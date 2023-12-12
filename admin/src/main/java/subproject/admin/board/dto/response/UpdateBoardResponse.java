package subproject.admin.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.dto.item.BoardItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardResponse {
    private BoardItem items;
}
