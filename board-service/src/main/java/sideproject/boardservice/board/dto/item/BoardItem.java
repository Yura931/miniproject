package sideproject.boardservice.board.dto.item;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.board.entity.enums.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardItem {
    private final Long id;
    private final BoardEnabled boardEnabled;
    private final BoardVisible boardVisible;
    private final BoardType boardType;
    private final String boardTitle;
    private final String boardDescription;
    private final BoardCategoryEnabled boardCategoryEnabled;
    private final BoardFileEnabled boardFileEnabled;
    private final BoardCommentEnabled boardCommentEnabled;
    private final List<BoardCategory> categories;

    public static BoardItem boardEntityToDto(Board board) {
        return new BoardItem(
                board.getId(),
                board.getBoardEnabled(),
                board.getBoardVisible(),
                board.getBoardType(),
                board.getBoardTitle(),
                board.getBoardDescription(),
                board.getBoardCategoryEnabled(),
                board.getBoardFileEnabled(),
                board.getBoardCommentEnabled(),
                board.getCategories()
        );
    }
}
