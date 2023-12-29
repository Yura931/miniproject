package sideproject.boardservice.board.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.dto.request.SearchBoardRequest;
import sideproject.boardservice.common.enums.SortDirection;


public record SearchBoardDto(
        SortDirection sortDirection,
        BoardSortCondition boardSortCondition,
        Pageable pageable
)
{
    public static SearchBoardDto from(SearchBoardRequest request) {

        return new SearchBoardDto(
                SortDirection.valueOf(request.getSortDirection()),
                BoardSortCondition.valueOf(request.getBoardSortCondition()),
                PageRequest.of(request.getPageNo(), request.getPageSize())
        );
    }

    public static SearchBoardDto of(String title, String content, SortDirection sortDirection, BoardSortCondition sortCondition, int pageNo, int pageSize) {
        return new SearchBoardDto(
                sortDirection,
                sortCondition,
                PageRequest.of(pageNo, pageSize)
        );
    }
}
