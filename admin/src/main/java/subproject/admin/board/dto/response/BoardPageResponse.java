package subproject.admin.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.Page;
import subproject.admin.board.dto.item.BoardPageItem;
import subproject.admin.board.entity.Board;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardPageResponse {
    private BoardPageItem items;
}
