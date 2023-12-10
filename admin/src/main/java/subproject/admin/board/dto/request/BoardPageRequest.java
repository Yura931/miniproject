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

    private String title;
    private String content;

    @NotNull
    private SortDirection sortDirection = SortDirection.ASC;
    @NotNull
    private BoardSortCondition sortCondition = BoardSortCondition.CREATE_AT;
    @NotNull
    private Integer pageNo = 0;
    @NotNull
    private Integer pageSize = 10;
}
