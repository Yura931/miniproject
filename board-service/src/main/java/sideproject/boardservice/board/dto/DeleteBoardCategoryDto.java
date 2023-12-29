package sideproject.boardservice.board.dto;

import java.util.UUID;

public record DeleteBoardCategoryDto (
        Long boardId,
        UUID categoryId
) {
    public static DeleteBoardCategoryDto of(Long boardId, UUID categoryId) {
        return new DeleteBoardCategoryDto(
                boardId,
                categoryId
        );
    }
}
