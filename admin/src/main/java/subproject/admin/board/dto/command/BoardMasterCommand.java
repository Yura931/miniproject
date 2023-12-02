package subproject.admin.board.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import subproject.admin.board.entity.BoardMaster;
import subproject.admin.board.entity.Enabled;

import java.util.UUID;


public record BoardMasterCommand (
        Enabled boardEnabled,
        Enabled boardVisible,
        String boardType,
        String boardMasterTitle,
        String boardMasterDescription,
        Enabled boardCategoryEnabled,
        Enabled boardFileEnabled,
        Enabled boardCommentEnabled,
        Enabled boardReplyCommentEnabled
){

    public static BoardMasterCommand of(Enabled boardEnabled, Enabled boardVisible, String boardType, String boardMasterTitle,
                                        String boardMasterDescription, Enabled boardCategoryEnabled, Enabled boardFileEnabled,
                                        Enabled boardCommentEnabled, Enabled boardReplyCommentEnabled) {
        return new BoardMasterCommand(
                boardEnabled,
                boardVisible,
                boardType,
                boardMasterTitle,
                boardMasterDescription,
                boardCategoryEnabled,
                boardFileEnabled,
                boardCommentEnabled,
                boardReplyCommentEnabled
        );
    }

}
