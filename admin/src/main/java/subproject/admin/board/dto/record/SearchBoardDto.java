package subproject.admin.board.dto.record;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.request.SearchBoardRequest;
import subproject.admin.common.enums.SortDirection;


public record SearchBoardDto(
        SortDirection sortDirection,
        BoardSortCondition sortCondition,
        Pageable pageable
)
{
    public static SearchBoardDto from(SearchBoardRequest request) {

        return new SearchBoardDto(
                SortDirection.valueOf(request.getSortDirection()),
                BoardSortCondition.valueOf(request.getSortCondition()),
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
