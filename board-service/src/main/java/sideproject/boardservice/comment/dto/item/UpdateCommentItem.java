package sideproject.boardservice.comment.dto.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sideproject.boardservice.comment.entity.Comment;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateCommentItem {
    private UUID commentId;

    public static UpdateCommentItem CommentEntityToDto(Comment comment) {
        return new UpdateCommentItem(
                comment.getId()
        );
    }
}
