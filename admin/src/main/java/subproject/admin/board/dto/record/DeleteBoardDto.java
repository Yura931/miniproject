package subproject.admin.board.dto.record;

import java.util.UUID;

public record DeleteBoardDto(
        Long id
) {

    public static DeleteBoardDto of (Long id) {
        return new DeleteBoardDto(id);
    }
}
