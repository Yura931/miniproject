package subproject.admin.board.dto;

import subproject.admin.board.entity.enums.Enabled;

import java.util.UUID;

public record UpdateBoardDto(
        UUID id,
        Enabled boardEnabled,
        Enabled boardVisible,
        String boardType,
        String boardTitle,
        String boardDescription,
        Enabled boardCategoryEnabled,
        Enabled boardFileEnabled,
        Enabled boardCommentEnabled,
        Enabled boardReplyCommentEnabled
) {
    public static UpdateBoardDto of(
            UUID id,
            Enabled boardEnabled,
            Enabled boardVisible,
            String boardType,
            String boardTitle,
            String boardDescription,
            Enabled boardCategoryEnabled,
            Enabled boardFileEnabled,
            Enabled boardCommentEnabled,
            Enabled boardReplyCommentEnabled
    ) {
        return new UpdateBoardDto(
                id,
                boardEnabled,
                boardVisible,
                boardType,
                boardTitle,
                boardDescription,
                boardCategoryEnabled,
                boardFileEnabled,
                boardCommentEnabled,
                boardReplyCommentEnabled
        );
    }
}
