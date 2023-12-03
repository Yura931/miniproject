package subproject.admin.board.dto.response;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import subproject.admin.board.entity.Board;

@RequiredArgsConstructor
public class BoardPageResponse {
    private final Page<Board> items;
}
