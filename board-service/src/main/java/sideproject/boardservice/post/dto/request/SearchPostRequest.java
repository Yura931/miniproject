package sideproject.boardservice.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.common.enums.SortDirection;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchPostRequest {
    private int pageNo = 0;
    private int pageSize = 10;
    private SortDirection sortDirection = SortDirection.ASC;
    private String title;
    private String content;
    private String createdBy;
    private Long boardId;
}
