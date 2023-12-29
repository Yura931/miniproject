package sideproject.boardservice.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBoardRequest {

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
