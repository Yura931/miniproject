package subproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.common.enums.SortDirection;

@AllArgsConstructor
@Getter
public class BoardPageRequest {
    @NotBlank
    private String title;
    private String content;
    @NotNull
    private SortDirection sortDirection;
    @NotNull
    private BoardSortCondition sortCondition;
    @NotNull
    private int pageNo = 0;
    @NotNull
    private int PageSize = 10;
}
