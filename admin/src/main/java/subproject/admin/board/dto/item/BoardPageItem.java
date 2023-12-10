package subproject.admin.board.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import subproject.admin.board.entity.Board;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardPageItem {
    private final Integer number;
    private final Integer size;
    private final Long totalElements;
    private final Integer totalPages;

    private final List<Board> contents;

    public static BoardPageItem boardEntityToDto(Page<Board> board) {
        return new BoardPageItem(
                board.getNumber(),
                board.getSize(),
                board.getTotalElements(),
                board.getTotalPages(),
                board.getContent()
        );
    }
}
