package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.item.BoardIdItem;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardIdResponse {
    private BoardIdItem items;
}
