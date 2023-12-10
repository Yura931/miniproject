package subproject.admin.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import subproject.admin.board.dto.item.BoardPageItem;
import subproject.admin.board.entity.Board;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class BoardPageResponse {
    private BoardPageItem items;
}
