package subproject.admin.post.dto;


import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.post.dto.request.RegisterPostRequest;

public record RegisterPostDto(
        Board board,
        BoardCategory boardCategory,
        String postTitle,
        String postContent
) {
    public static RegisterPostDto of(Board board, BoardCategory boardCategory, String postTitle, String postContent) {
        return new RegisterPostDto(board, boardCategory, postTitle, postContent);
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
