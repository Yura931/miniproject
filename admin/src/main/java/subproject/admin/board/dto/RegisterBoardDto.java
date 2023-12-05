package subproject.admin.board.dto;

import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.entity.QBoard;
import subproject.admin.board.entity.enums.Enabled;

import java.util.List;
import java.util.stream.Collectors;


public record RegisterBoardDto(
        Enabled boardEnabled,
        Enabled boardVisible,
        String boardType,
        String boardTitle,
        String boardDescription,
        Enabled boardCategoryEnabled,
        Enabled boardFileEnabled,
        Enabled boardCommentEnabled,
        Enabled boardReplyCommentEnabled,
        List<CategoryDto> categories
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
                        .map(category -> CategoryDto.from(category))
                        .toList()
        );
    }
}
