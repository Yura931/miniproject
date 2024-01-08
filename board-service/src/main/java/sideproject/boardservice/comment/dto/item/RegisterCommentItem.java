package sideproject.boardservice.comment.dto.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sideproject.boardservice.comment.entity.Comment;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegisterCommentItem {
    private String comment;

    public static RegisterCommentItem CommentEntityToDto(Comment comment) {
        return new RegisterCommentItem(
                comment.getComment()
        );
    }
}
