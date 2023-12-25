package subproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateBoardRequest {

    @NotNull
    private Enabled boardEnabled;

    @NotNull
    private Enabled boardVisible;

    @NotNull
    private BoardType boardType;

    @NotBlank
    private String boardTitle;

    private String boardDescription;

    @NotNull
    private Enabled boardCategoryEnabled;

    @NotNull
    private Enabled boardFileEnabled;

    @NotNull
    private Enabled boardCommentEnabled;

    @NotNull
    private Enabled boardReplyCommentEnabled;
}
