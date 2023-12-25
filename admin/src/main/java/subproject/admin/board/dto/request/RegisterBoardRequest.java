package subproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.enums.EnumDto;

import java.beans.IntrospectionException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBoardRequest {

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

    private List<String> categories = new ArrayList<>();

    @Override
    public String toString() {
        return "RegisterBoardRequest{" +
                "boardEnabled=" + boardEnabled +
                ", boardVisible=" + boardVisible +
                ", boardType=" + boardType +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardDescription='" + boardDescription + '\'' +
                ", boardCategoryEnabled=" + boardCategoryEnabled +
                ", boardFileEnabled=" + boardFileEnabled +
                ", boardCommentEnabled=" + boardCommentEnabled +
                ", boardReplyCommentEnabled=" + boardReplyCommentEnabled +
                ", categories=" + categories +
                '}';
    }

}
