package subproject.admin.board.dto;

import java.util.UUID;

public record SearchBoardDto(
        UUID id
) {
    public static SearchBoardDto from (UUID id) {
        return new SearchBoardDto(id);
    }
}
