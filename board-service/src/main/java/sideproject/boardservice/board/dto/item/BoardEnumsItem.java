package sideproject.boardservice.board.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.dto.enums.BoardSearchCondition;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;
import sideproject.boardservice.common.enums.EnumDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEnumsItem {

    List<EnumDto> boardSortConditionList = BoardSortCondition.getBoardSortCondition();
    List<EnumDto> boardSearchConditionList = BoardSearchCondition.getBoardSearchCondition();
    List<EnumDto> boardType = BoardType.getBoardTypeList();
    List<EnumDto> boardEnabled = BoardEnabled.getEnabledList();
    public static BoardEnumsItem enums() {
        return new BoardEnumsItem();
    }
}
