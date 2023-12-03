package subproject.admin.board.dto;

import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.request.BoardPageRequest;
import subproject.admin.common.enums.SortDirection;


public record BoardPageDto(
        String title,
        String content,
        SortDirection sortDirection,
        BoardSortCondition sortCondition,
        int pageNo,
        int pageSize
)
{
    public static BoardPageDto from(BoardPageRequest request) {
        return new BoardPageDto(
                request.getTitle(),
                request.getContent(),
                request.getSortDirection(),
                request.getSortCondition(),
                request.getPageNo(),
                request.getPageSize()
        );
    }
}
