package sideproject.boardservice.board.dto;


public record BoardCategoryDto(
        String categoryName
) {
    public static BoardCategoryDto from(String categoryName) {
        return new BoardCategoryDto(categoryName);
    }
}
