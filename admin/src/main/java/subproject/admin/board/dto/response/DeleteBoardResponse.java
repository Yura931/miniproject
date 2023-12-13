package subproject.admin.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.board.dto.item.BoardItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardResponse {
    private BoardItem items;
}
