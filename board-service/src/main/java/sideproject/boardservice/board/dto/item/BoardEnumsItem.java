package sideproject.boardservice.board.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.board.dto.enums.BoardSearchCondition;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;
import sideproject.boardservice.common.enums.EnumDto;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardEnumsItem {

    private final List<EnumDto> boardSortConditionList = BoardSortCondition.getBoardSortCondition();
    private final List<EnumDto> boardSearchConditionList = BoardSearchCondition.getBoardSearchCondition();
    private final List<EnumDto> boardType = BoardType.getBoardTypeList();
    private final List<EnumDto> boardEnabled = BoardEnabled.getEnabledList();
    public static BoardEnumsItem enums() {
        return new BoardEnumsItem();
    }
}
