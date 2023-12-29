package sideproject.boardservice.board.dto;

import java.util.UUID;

public record UpdateBoardCategoryDto(
        Long boardId,
        UUID boardCategoryId,
        String boardCategoryName
) {
    public static UpdateBoardCategoryDto of(Long boardId, UUID categoryId, String categoryName) {
        return new UpdateBoardCategoryDto(
                boardId,
                categoryId,
                categoryName
        );
    }
}
