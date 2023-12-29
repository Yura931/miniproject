package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.item.BoardEnumsItem;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardEnumsResponse {
    BoardEnumsItem items;
}


