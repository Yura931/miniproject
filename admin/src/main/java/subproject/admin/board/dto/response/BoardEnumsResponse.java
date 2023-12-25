package subproject.admin.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.board.dto.item.BoardEnumsItem;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.enums.EnumDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardEnumsResponse {
    BoardEnumsItem items;
}


