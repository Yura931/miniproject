package sideproject.boardservice.board.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.projection.BoardSelectorDto;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardSelectorItem {
    private List<BoardSelectorDto> boardSelector;

    public BoardSelectorItem(List<BoardSelectorDto> boardSelector) {
        this.boardSelector = boardSelector;
    }

    public static BoardSelectorItem boardEntityToDto(List<BoardSelectorDto> boardSelector) {
        return new BoardSelectorItem(boardSelector);
    }
}
