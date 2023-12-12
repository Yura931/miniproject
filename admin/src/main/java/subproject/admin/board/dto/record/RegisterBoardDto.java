package subproject.admin.board.dto.record;

import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;

import java.util.List;


public record RegisterBoardDto(
        Enabled boardEnabled,
        Enabled boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        Enabled boardCategoryEnabled,
        Enabled boardFileEnabled,
        Enabled boardCommentEnabled,
        Enabled boardReplyCommentEnabled,
        List<BoardCategoryDto> categories
){

    public static RegisterBoardDto from(RegisterBoardRequest request) {
        return new RegisterBoardDto(
                request.getBoardEnabled(),
                request.getBoardVisible(),
                request.getBoardType(),
                request.getBoardTitle(),
                request.getBoardDescription(),
                request.getBoardCategoryEnabled(),
                request.getBoardFileEnabled(),
                request.getBoardCommentEnabled(),
                request.getBoardReplyCommentEnabled(),
                request.getCategories()
                        .stream()
                        .map(category -> BoardCategoryDto.from(category))
                        .toList()
        );
    }
}
