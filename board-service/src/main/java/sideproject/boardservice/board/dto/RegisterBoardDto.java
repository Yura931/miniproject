package sideproject.boardservice.board.dto;

import sideproject.boardservice.board.dto.request.RegisterBoardRequest;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;

import java.util.List;


public record RegisterBoardDto(
        BoardEnabled boardEnabled,
        BoardEnabled boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        BoardEnabled boardCategoryEnabled,
        BoardEnabled boardFileEnabled,
        BoardEnabled boardCommentEnabled,
        BoardEnabled boardReplyCommentEnabled,
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
