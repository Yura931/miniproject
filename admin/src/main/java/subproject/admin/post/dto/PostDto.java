package subproject.admin.post.dto;


import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.entity.Category;

public record PostDto(
        Board board,
        Category category,
        String postTitle,
        String postContent
) {
    public static PostDto of(Board board, Category category, String postTitle, String postContent) {
        return new PostDto(board, category, postTitle, postContent);
    }
}
