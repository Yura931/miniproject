package sideproject.boardservice.board.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.projection.BoardSelectorDto;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardIdItem {
    private List<BoardSelectorDto> boardSelector;

    public BoardIdItem(List<BoardSelectorDto> boardSelector) {
        this.boardSelector = boardSelector;
    }

    public static BoardIdItem boardEntityToDto(List<BoardSelectorDto> boardSelector) {
        return new BoardIdItem(boardSelector);
    }
}
