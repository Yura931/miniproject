package subproject.admin.board.dto.response;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.dto.item.BoardItem;

@RequiredArgsConstructor
public class RegisterBoardResponse {
    private final BoardItem item;
}
