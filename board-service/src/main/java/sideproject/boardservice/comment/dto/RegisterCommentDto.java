package sideproject.boardservice.comment.dto;

import sideproject.boardservice.comment.dto.request.RegisterCommentRequest;

import java.util.UUID;

public record RegisterCommentDto(
        UUID postId,
        String comment
) {

    public static RegisterCommentDto of(UUID postId, RegisterCommentRequest request) {
        return new RegisterCommentDto(
          postId,
          request.getComment()
        );
    }
}
