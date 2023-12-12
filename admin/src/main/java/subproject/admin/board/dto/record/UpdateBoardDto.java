package subproject.admin.board.dto.record;

import subproject.admin.board.dto.request.UpdateBoardRequest;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;

import java.util.UUID;

public record UpdateBoardDto(
        Long id,
        Enabled boardEnabled,
        Enabled boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        Enabled boardCategoryEnabled,
        Enabled boardFileEnabled,
        Enabled boardCommentEnabled,
        Enabled boardReplyCommentEnabled
) {
    public static UpdateBoardDto of(
            Long id,
            UpdateBoardRequest request
    ) {
        return new UpdateBoardDto(
                id,
                request.getBoardEnabled(),
                request.getBoardVisible(),
                request.getBoardType(),
                request.getBoardTitle(),
                request.getBoardDescription(),
                request.getBoardCategoryEnabled(),
                request.getBoardFileEnabled(),
                request.getBoardCommentEnabled(),
                request.getBoardReplyCommentEnabled()
        );
    }
}
