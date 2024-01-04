package sideproject.boardservice.post.dto;

import sideproject.boardservice.post.dto.request.UpdatePostRequest;

import java.util.UUID;

public record UpdatePostDto (
        UUID postId,
        String postTitle,
        String postContent,
        String categoryName
) {

    public static UpdatePostDto of (UUID postId, UpdatePostRequest request) {
        return new UpdatePostDto(
                postId,
                request.getPostTitle(),
                request.getPostContent(),
                request.getCategoryName()
        );
    }
}
