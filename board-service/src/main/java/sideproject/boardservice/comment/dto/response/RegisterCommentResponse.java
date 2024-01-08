package sideproject.boardservice.comment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.comment.dto.item.RegisterCommentItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegisterCommentResponse {
    private RegisterCommentItem items;
}
