package subproject.admin.post.dto.record;


import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.file.dto.FileDto;
import subproject.admin.post.dto.request.RegisterPostRequest;
import subproject.admin.post.dto.request.SearchPostRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record RegisterPostDto(
        Long boardId,
        UUID categoryId,
        String postTitle,
        String postContent,
        Long viewCount,
        List<FileDto> fileDtos
) {
    public static RegisterPostDto of(Long boardId, RegisterPostRequest request, Long viewCount, List<FileDto> fileDtos) {

        return new RegisterPostDto(
                boardId,
                request.getCategoryId(),
                request.getPostTitle(),
                request.getPostContent(),
                viewCount, fileDtos);
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
