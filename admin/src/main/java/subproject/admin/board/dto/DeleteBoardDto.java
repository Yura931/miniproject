package subproject.admin.board.dto;

import java.util.UUID;

public record DeleteBoardDto(
        UUID id
) {

    public static DeleteBoardDto of (UUID id) {
        return new DeleteBoardDto(id);
    }
}
