package sideproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.admin.board.dto.enums.BoardEnabled;
import sideproject.admin.board.dto.enums.BoardType;


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
