package sideproject.boardservice.post.dto;


import sideproject.boardservice.post.dto.request.RegisterPostRequest;

import java.util.List;
import java.util.UUID;

public record RegisterPostDto(
        Long boardId,
        UUID categoryId,
        String postTitle,
        String postContent,
        Long viewCount
) {
    public static RegisterPostDto of(Long boardId, RegisterPostRequest request, Long viewCount) {

        return new RegisterPostDto(
                boardId,
                request.getCategoryId(),
                request.getPostTitle(),
                request.getPostContent(),
                viewCount);
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
