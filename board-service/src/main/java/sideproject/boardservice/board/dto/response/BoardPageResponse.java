package sideproject.boardservice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.board.dto.item.BoardPageItem;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BoardPageResponse {
    private BoardPageItem items;
}
