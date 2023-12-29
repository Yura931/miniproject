package sideproject.boardservice.post.repository;

import org.springframework.data.domain.Page;
import sideproject.boardservice.post.dto.SearchPostDto;
import sideproject.boardservice.post.projection.SearchPostPageDto;

public interface PostRepositoryCustom {

    /**
     * 게시물 전체 검색
     * @param dto
     * @return
     */
    Page<SearchPostPageDto> searchAll(SearchPostDto dto);

}
