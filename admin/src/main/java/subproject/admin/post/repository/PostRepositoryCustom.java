package subproject.admin.post.repository;

import org.springframework.data.domain.Page;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.projection.SearchPostPageDto;

public interface PostRepositoryCustom {

    /**
     * 게시물 전체 검색
     * @param dto
     * @return
     */
    Page<SearchPostPageDto> searchAll(SearchPostDto dto);

}
