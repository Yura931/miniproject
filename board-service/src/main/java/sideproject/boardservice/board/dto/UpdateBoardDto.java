package sideproject.boardservice.board.dto;

import sideproject.boardservice.board.dto.request.UpdateBoardRequest;
import sideproject.boardservice.board.entity.enums.*;

public record UpdateBoardDto(
        Long id,
        BoardEnabled boardEnabled,
        BoardVisible boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        BoardCategoryEnabled boardCategoryEnabled,
        BoardFileEnabled boardFileEnabled,
        BoardCommentEnabled boardCommentEnabled
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
                request.getBoardCommentEnabled()
        );
    }
}
