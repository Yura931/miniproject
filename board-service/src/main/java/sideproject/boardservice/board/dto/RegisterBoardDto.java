package sideproject.boardservice.board.dto;

import sideproject.boardservice.board.dto.request.RegisterBoardRequest;
import sideproject.boardservice.board.entity.enums.*;

import java.util.List;


public record RegisterBoardDto(
        BoardEnabled boardEnabled,
        BoardVisible boardVisible,
        BoardType boardType,
        String boardTitle,
        String boardDescription,
        BoardCategoryEnabled boardCategoryEnabled,
        BoardFileEnabled boardFileEnabled,
        BoardCommentEnabled boardCommentEnabled,
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
                request.getCategories()
                        .stream()
                        .map(category -> BoardCategoryDto.from(category))
                        .toList()
        );
    }
}
