package sideproject.boardservice.comment.dto;

import sideproject.boardservice.comment.dto.request.UpdateCommentRequest;

import java.util.UUID;

public record UpdateCommentDto (
        UUID postId,
        UUID commentId,
        String content
) {

    public static UpdateCommentDto of(UUID postId, UUID commentId, UpdateCommentRequest request) {
        return new UpdateCommentDto(
                postId,
                commentId,
                request.getContent()
        );
    }
}
