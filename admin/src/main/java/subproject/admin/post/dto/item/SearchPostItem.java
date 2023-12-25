package subproject.admin.post.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import subproject.admin.post.projection.SearchPostPageDto;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(of = { "number", "size", "totalElements", "totalPages", "pageable", "contents"})
public class SearchPostItem {
    private final Integer number;
    private final Integer size;
    private final Long totalElements;
    private final Integer totalPages;
    private final Pageable pageable;
    private final List<SearchPostPageDto> contents;
    public static SearchPostItem PostEntityToDto(Page<SearchPostPageDto> post) {
        return new SearchPostItem(
                post.getNumber(),
                post.getSize(),
                post.getTotalElements(),
                post.getTotalPages(),
                post.getPageable(),
                post.getContent()
        );
    }
}
