package sideproject.boardservice.post.dto;


import sideproject.boardservice.post.dto.request.RegisterPostRequest;

import java.util.UUID;

public record RegisterPostDto(
        UUID postId,
        Long boardId,
        UUID categoryId,
        String postTitle,
        String postContent,
        Long viewCount
) {
    public static RegisterPostDto of(Long boardId, RegisterPostRequest request, Long viewCount) {
        return new RegisterPostDto(
                UUID.randomUUID(),
                boardId,
                request.getCategoryId(),
                request.getPostTitle(),
                request.getPostContent(),
                viewCount
        );
    }

    public static RegisterPostDto from(RegisterPostRequest request) {
        return null;
    }
}
