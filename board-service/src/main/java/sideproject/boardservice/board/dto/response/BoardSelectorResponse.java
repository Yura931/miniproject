package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.item.BoardSelectorItem;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardSelectorResponse {
    private BoardSelectorItem items;
}
