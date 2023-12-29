package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.item.BoardItem;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardResponse {
    private BoardItem items;
}
