package sideproject.boardservice.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.board.entity.enums.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateBoardRequest {

    @NotNull
    private BoardEnabled boardEnabled;

    @NotNull
    private BoardVisible boardVisible;

    @NotNull
    private BoardType boardType;

    @NotBlank
    private String boardTitle;

    private String boardDescription;

    @NotNull
    private BoardCategoryEnabled boardCategoryEnabled;

    @NotNull
    private BoardFileEnabled boardFileEnabled;

    @NotNull
    private BoardCommentEnabled boardCommentEnabled;

}
