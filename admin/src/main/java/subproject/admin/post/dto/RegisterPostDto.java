package subproject.admin.post.dto;


import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.Category;
import subproject.admin.post.dto.request.RegisterPostRequest;

public record RegisterPostDto(
        Board board,
        Category category,
        String postTitle,
        String postContent
) {
    public static RegisterPostDto of(Board board, Category category, String postTitle, String postContent) {
        return new RegisterPostDto(board, category, postTitle, postContent);
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
