package sideproject.boardservice.board.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sideproject.boardservice.board.dto.enums.BoardSearchCondition;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;
import sideproject.boardservice.common.enums.EnumDto;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(of = { "number", "size", "totalElements", "totalPages", "contents"})
public class BoardPageItem {
    private final Integer number;
    private final Integer size;
    private final Long totalElements;
    private final Integer totalPages;
    private final Pageable pageable;

    private final List<SearchBoardPageDto> contents;

    List<EnumDto> boardSortConditionList = BoardSortCondition.getBoardSortCondition();
    List<EnumDto> boardSearchConditionList = BoardSearchCondition.getBoardSearchCondition();
    List<EnumDto> boardTypeList = BoardType.getBoardTypeList();
    List<EnumDto> boardEnabledList = BoardEnabled.getEnabledList();

    public static BoardPageItem boardEntityToDto(Page<SearchBoardPageDto> board) {
        return new BoardPageItem(
                board.getNumber(),
                board.getSize(),
                board.getTotalElements(),
                board.getTotalPages(),
                board.getPageable(),
                board.getContent()
        );
    }
}
