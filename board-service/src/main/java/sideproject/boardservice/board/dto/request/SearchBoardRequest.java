package sideproject.boardservice.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import sideproject.boardservice.board.dto.enums.BoardSearchCondition;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.common.enums.SortDirection;


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
    private String boardSortCondition = BoardSortCondition.CREATE_AT.name();

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;
}

