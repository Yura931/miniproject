package sideproject.boardservice.comment.dto;

import java.util.UUID;

public record DeleteCommentDto (
        UUID commentId
) {

    public static DeleteCommentDto from(UUID commentId) {
        return new DeleteCommentDto(commentId);
    }
}
