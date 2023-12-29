package sideproject.boardservice.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateBoardRequest {

    @NotNull
    private BoardEnabled boardEnabled;

    @NotNull
    private BoardEnabled boardVisible;

    @NotNull
    private BoardType boardType;

    @NotBlank
    private String boardTitle;

    private String boardDescription;

    @NotNull
    private BoardEnabled boardCategoryEnabled;

    @NotNull
    private BoardEnabled boardFileEnabled;

    @NotNull
    private BoardEnabled boardCommentEnabled;

    @NotNull
    private BoardEnabled boardReplyCommentEnabled;
}
