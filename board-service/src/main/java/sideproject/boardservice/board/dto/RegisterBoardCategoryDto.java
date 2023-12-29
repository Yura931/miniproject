package sideproject.boardservice.board.dto;

public record RegisterBoardCategoryDto(
        Long boardId,
        String categoryName
) {

    public static RegisterBoardCategoryDto of(Long boardId, String categoryName) {
        return new RegisterBoardCategoryDto(
                boardId, categoryName
        );
    }
}
