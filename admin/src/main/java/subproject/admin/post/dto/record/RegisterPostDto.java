package subproject.admin.post.dto.record;


import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.file.dto.FileDto;
import subproject.admin.post.dto.request.RegisterPostRequest;

import java.util.List;

public record RegisterPostDto(
        Board board,
        BoardCategory boardCategory,
        String postTitle,
        String postContent,
        List<FileDto> fileDtos
) {
    public static RegisterPostDto of(Board board, BoardCategory boardCategory, String postTitle, String postContent, List<FileDto> fileDtos) {
        return new RegisterPostDto(board, boardCategory, postTitle, postContent, fileDtos);
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
