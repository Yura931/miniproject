package sideproject.boardservice.post.dto.request;

import lombok.*;
import sideproject.boardservice.common.enums.SortDirection;


@Getter
@Setter
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
