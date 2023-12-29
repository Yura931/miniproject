package sideproject.boardservice.board.dto;

import sideproject.boardservice.board.dto.request.UpdateBoardRequest;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;

public record UpdateBoardDto(
        Long id,
        BoardEnabled boardEnabled,
        BoardEnabled boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        BoardEnabled boardCategoryEnabled,
        BoardEnabled boardFileEnabled,
        BoardEnabled boardCommentEnabled,
        BoardEnabled boardReplyCommentEnabled
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
