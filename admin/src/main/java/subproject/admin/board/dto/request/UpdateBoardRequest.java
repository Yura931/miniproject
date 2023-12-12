package subproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {

    @NotBlank
    private Enabled boardEnabled;

    @NotBlank
    private Enabled boardVisible;

    @NotBlank
    private BoardType boardType;

    @NotBlank
    private String boardTitle;

    private String boardDescription;

    @NotBlank
    private Enabled boardCategoryEnabled;

    @NotBlank
    private Enabled boardFileEnabled;

    @NotBlank
    private Enabled boardCommentEnabled;

    @NotBlank
    private Enabled boardReplyCommentEnabled;
}
