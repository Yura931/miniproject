package subproject.admin.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.projection.SearchBoardPageDto;

public interface BoardRepositoryCustom {
    Page<SearchBoardPageDto> searchAll(SearchBoardDto condition, Pageable pageable);

}
