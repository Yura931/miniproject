package subproject.admin.board.dto.record;

import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.request.SearchBoardRequest;
import subproject.admin.common.enums.SortDirection;


public record SearchBoardDto(
        String title,
        String content,
        SortDirection sortDirection,
        BoardSortCondition sortCondition,
        int pageNo,
        int pageSize
)
{
    public static SearchBoardDto from(SearchBoardRequest request) {
        return new SearchBoardDto(
                request.getTitle(),
                request.getContent(),
                request.getSortDirection(),
                request.getSortCondition(),
                request.getPageNo(),
                request.getPageSize()
        );
    }

    public static SearchBoardDto of(String title, String content, SortDirection sortDirection, BoardSortCondition sortCondition, int pageNo, int pageSize) {
        return new SearchBoardDto(
                title,
                content,
                sortDirection,
                sortCondition,
                pageNo,
                pageSize
        );
    }
}
