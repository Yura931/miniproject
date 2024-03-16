package sideproject.boardservice.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.entity.enums.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBoardRequest {

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
                ", categories=" + categories +
                '}';
    }

}
