package subproject.admin.board.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.board.dto.enums.BoardSearchCondition;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.response.BoardEnumsResponse;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.enums.EnumDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEnumsItem {
    List<EnumDto> boardTypeList = BoardType.getBoardTypeList();
    List<EnumDto> enabledList = Enabled.getEnabledList();
    List<EnumDto> boardSortConditionList = BoardSortCondition.getBoardSortCondition();
    List<EnumDto> boardSearchConditionList = BoardSearchCondition.getBoardSearchCondition();

    public static BoardEnumsItem enums() {
        return new BoardEnumsItem();
    }
}
