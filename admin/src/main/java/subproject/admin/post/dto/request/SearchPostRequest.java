package subproject.admin.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import subproject.admin.common.enums.SortDirection;

@Getter
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
