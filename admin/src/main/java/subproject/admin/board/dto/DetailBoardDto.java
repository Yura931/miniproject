package subproject.admin.board.dto;

import java.util.UUID;

public record DetailBoardDto(
        UUID id
) {
    public static DetailBoardDto from (UUID id) {
        return new DetailBoardDto(id);
    }
}
