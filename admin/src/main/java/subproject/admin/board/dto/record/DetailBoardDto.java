package subproject.admin.board.dto.record;

import java.util.UUID;

public record DetailBoardDto(
        Long id
) {
    public static DetailBoardDto from (Long id) {
        return new DetailBoardDto(id);
    }
}
