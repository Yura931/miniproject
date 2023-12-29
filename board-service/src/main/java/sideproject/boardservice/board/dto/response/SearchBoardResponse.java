package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.item.BoardItem;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchBoardResponse {
    private BoardItem items;
}
