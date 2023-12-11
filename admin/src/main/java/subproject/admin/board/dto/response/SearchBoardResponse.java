package subproject.admin.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.dto.item.BoardItem;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchBoardResponse {
    private BoardItem items;
}
