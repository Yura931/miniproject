package subproject.admin.board.dto.record;


public record BoardCategoryDto(
        String categoryName
) {
    public static BoardCategoryDto from(String categoryName) {
        return new BoardCategoryDto(categoryName);
    }
}
