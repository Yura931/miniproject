package subproject.admin.board.dto;


import java.util.List;
public record BoardCategoryDto(
        String boardCategoryName
) {
    public static BoardCategoryDto from(String boardCategoryName) {
        return new BoardCategoryDto(boardCategoryName);
    }
}
