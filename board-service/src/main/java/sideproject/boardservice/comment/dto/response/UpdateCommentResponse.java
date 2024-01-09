package sideproject.boardservice.comment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.comment.dto.item.UpdateCommentItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateCommentResponse {
    UpdateCommentItem items;
}
