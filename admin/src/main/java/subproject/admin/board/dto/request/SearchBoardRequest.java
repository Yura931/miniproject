package subproject.admin.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import subproject.admin.board.dto.enums.BoardSearchCondition;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.common.enums.SortDirection;

@Getter
@AllArgsConstructor
@ToString
public class SearchBoardRequest {
    private String searchWord;

    private String boardSearchCondition = BoardSearchCondition.ALL.name();

    @NotNull
    @NotBlank
    private String sortDirection = SortDirection.DESC.name();

    @NotNull
    @NotBlank
    private String sortCondition = BoardSortCondition.CREATE_AT.name();

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;
}

